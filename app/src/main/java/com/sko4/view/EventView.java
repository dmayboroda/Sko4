package com.sko4.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sko4.BuildConfig;
import com.sko4.EventDetailsActivity;
import com.sko4.R;
import com.sko4.RxUtil;
import com.sko4.api.ApiService;
import com.sko4.di.component.AppComponent;
import com.sko4.di.component.DaggerEventsComponent;
import com.sko4.di.component.EventsComponent;
import com.sko4.di.module.EventsModule;
import com.sko4.model.Event;
import com.sko4.model.EventData;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Event view.
 * Created by Mayboroda on 6/4/16.
 */
public class EventView extends CoordinatorLayout implements Action1<EventData> {

    public static final String TAG = EventView.class.getSimpleName();

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private final PublishSubject<String> eventSubject = PublishSubject.create();

    @Inject Picasso picasso;
    @Inject ApiService apiService;

    @Bind(R.id.event_progress)  ProgressBar progressBar;
    @Bind(R.id.event_switcher)  StateViewSwitcher switcher;
    @Bind(R.id.event_error)     TextView error;
    @Bind(R.id.event_info)      EventInfo eventInfo;
    @Bind(R.id.event_map)       EventMap eventMap;
    @Bind(R.id.event_desc)      EventDesc eventDesc;
    @Bind(R.id.event_fab)       FloatingActionButton actionButton;
    @Bind(R.id.artist_stack)    ArtistStack artistStack;

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            AppComponent appComponent = ((EventDetailsActivity)context)
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
                    })).build();
            component.inject(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        error.setText(getContext().getText(R.string.error_nothing));
        progressBar.setIndeterminateDrawable(new IndeterminateProgressDrawable(getContext()));
        switcher.setDisplayedChildId(R.id.event_progress);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        subscriptions.add(eventSubject
                .flatMap(EVENT_INFO)
                .subscribe(this));
        if (switcher.getDisplayedChildId() != R.id.event_progress) {
            switcher.setDisplayedChildId(R.id.event_progress);
        }
        String id = ((EventDetailsActivity)getContext()).getId();
        eventSubject.onNext(id);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }

    private Func1<String, Observable<EventData>> EVENT_INFO = new Func1<String, Observable<EventData>>() {
        @Override
        public Observable<EventData> call(String id) {
            return RxUtil.applySchedulers(apiService.getEventData(id))
                    .doOnError(ERROR)
                    .onErrorResumeNext(Observable.<EventData>empty());
        }
    };

    private Action1<Throwable> ERROR = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            switcher.setDisplayedChildId(R.id.event_error);
        }
    };

    @Override
    public void call(EventData eventData) {
        Event event = eventData.getData();
        if (event == null) {
            eventSubject.onError(new Throwable());
            return;
        }
        eventInfo.bind(event);
        eventMap.bind(event);
        eventDesc.bind(event);
        artistStack.bind(event.getArtists(), picasso);

        String tickets = event.getTickets();
        if (TextUtils.isEmpty(tickets)) {
            actionButton.setVisibility(GONE);
        } else {
            actionButton.setTag(tickets);
            actionButton.setVisibility(VISIBLE);
        }

        switcher.setDisplayedChildId(R.id.event_scroll);
    }

    @OnClick(R.id.event_fab)
    public void onActionButton(View view) {
        String tickets = (String) view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tickets));
        getContext().startActivity(intent);
    }
}
