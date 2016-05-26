package com.sko4.view;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chanel.component.AppComponent;
import com.chanel.view.StateViewSwitcher;
import com.sko4.BuildConfig;
import com.sko4.MainActivity;
import com.sko4.R;
import com.sko4.RxUtil;
import com.sko4.api.ApiService;
import com.sko4.api.EventsToList;
import com.sko4.di.DaggerEventsComponent;
import com.sko4.di.EventsComponent;
import com.sko4.di.EventsModule;
import com.sko4.model.Bindable;
import com.sko4.model.EventsWrapper;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Events view.
 * Created by Mayboroda.
 */
public class ItemsListView extends CoordinatorLayout implements ItemsAdapter.Chooser {

    public static final String TAG = ItemsListView.class.getSimpleName();

    public static final int ITEMS_COUNT = 1000;

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private final PublishSubject<Integer> limitSubject = PublishSubject.create();

    @Inject ApiService api;
    @Inject Picasso picasso;

    @Bind(R.id.progress)        ProgressBar progressBar;
    @Bind(R.id.switcher)        StateViewSwitcher switcher;
    @Bind(R.id.error_message)   TextView errorView;
    @Bind(R.id.items_recycler)  RecyclerView itemsList;
    @Bind(R.id.toolbar)         Toolbar toolbar;

    private ItemsAdapter adapter;

    public ItemsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            AppComponent appComponent = ((MainActivity)context)
                    .getAppComponent();
            EventsComponent component = DaggerEventsComponent.builder()
                    .appComponent(appComponent)
                    .eventsModule(new EventsModule(new Picasso.Listener() {
                        @Override
                        public void onImageLoadFailed(Picasso picasso,
                                                      Uri uri,
                                                      Exception exception) {
                            if (BuildConfig.DEBUG && !TextUtils.isEmpty(uri.toString())) {
                                Log.e(TAG, uri.toString());
                            }
                        }
                    }))
                    .build();
            component.inject(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        ((MainActivity)getContext()).setSupportActionBar(toolbar);
        errorView.setText(getContext().getText(R.string.error_nothing));
        progressBar.setIndeterminateDrawable(new IndeterminateProgressDrawable(getContext()));
        switcher.setDisplayedChildId(R.id.progress);

        adapter = new ItemsAdapter(picasso, this);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                switcher.setDisplayedChildId(
                        adapter.getItemCount() == 0
                                ? R.id.error_message
                                : R.id.items_recycler);
            }
        });
        itemsList.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsList.setAdapter(adapter);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        subscriptions.add(limitSubject
            .flatMap(EVENTS)
            .map(EventsToList.instance())
            .subscribe(adapter));
        if (switcher.getDisplayedChildId() != R.id.progress) {
            switcher.setDisplayedChildId(R.id.progress);
        }
        limitSubject.onNext(ITEMS_COUNT);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }


    private final Func1<Integer, Observable<EventsWrapper>> EVENTS = new Func1<Integer, Observable<EventsWrapper>>() {
        @Override
        public Observable<EventsWrapper> call(Integer limit) {
            return RxUtil.applySchedulers(api.getEvents(limit))
                    .doOnError(ERROR)
                    .onErrorResumeNext(Observable.<EventsWrapper>empty());
        }
    };

    private Action1<Throwable> ERROR = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            switcher.setDisplayedChildId(R.id.error_message);
            errorView.setText(throwable.getMessage());
        }
    };


    @Override
    public void onChoose(Bindable bindable) {

    }

}
