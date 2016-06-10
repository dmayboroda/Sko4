package com.sko4.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.sko4.EventDetailsActivity;
import com.sko4.R;
import com.sko4.api.ApiService;
import com.sko4.model.Event;
import com.sko4.model.EventsWrapper;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observable;

/**
 * Events view.
 * Created by Mayboroda.
 */
public class ItemsListView extends RxCoordinator<EventsWrapper> implements ItemsAdapter.Chooser {

    public static final int EXTRA_SPACE = 300;
    public static final String CITY_ID  = "49713";

    @Inject ApiService api;
    @Inject Picasso picasso;

    @Bind(R.id.items_recycler)  RecyclerView itemsList;

    private ItemsAdapter adapter;

    public ItemsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            component.inject(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
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
        itemsList.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return EXTRA_SPACE;
            }
        });
        itemsList.setAdapter(adapter);
    }

    @Override
    public void call(EventsWrapper wrapper) {
        adapter.publish(wrapper);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        eventSubject.onNext(CITY_ID);
    }

    @Override
    public Observable<EventsWrapper> createObservable(String value) {
        return api.getEvents(value);
    }

    @Override
    public void onChoose(View view, Event event) {
        EventDetailsActivity.navigate((AppCompatActivity) getContext(), view, event);
    }
}
