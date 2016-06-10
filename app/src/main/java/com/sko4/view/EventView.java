package com.sko4.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.sko4.EventDetailsActivity;
import com.sko4.R;
import com.sko4.api.ApiService;
import com.sko4.model.Event;
import com.sko4.model.EventData;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

/**
 * Event view.
 * Created by Mayboroda on 6/4/16.
 */
public class EventView extends RxCoordinator<EventData> {

    @Inject Picasso picasso;
    @Inject ApiService apiService;

    @Bind(R.id.event_info)          EventInfo eventInfo;
    @Bind(R.id.event_map)           EventMap eventMap;
    @Bind(R.id.event_desc)          EventDesc eventDesc;
    @Bind(R.id.event_fab)           FloatingActionButton actionButton;
    @Bind(R.id.artist_stack)        ArtistStack artistStack;

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            component.inject(this);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String id = ((EventDetailsActivity)getContext()).getId();
        eventSubject.onNext(id);
    }

    @Override
    public Observable<EventData> createObservable(String value) {
        return apiService.getEventData(value);
    }

    @Override
    public Action1<EventData> createAction() {
        return new Action1<EventData>() {
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
        };
    }

    @OnClick(R.id.event_fab)
    public void onActionButton(View view) {
        String tickets = (String) view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tickets));
        getContext().startActivity(intent);
    }
}
