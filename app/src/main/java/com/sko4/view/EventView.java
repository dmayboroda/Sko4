package com.sko4.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.sko4.DetailsActivity;
import com.sko4.EventActivity;
import com.sko4.R;
import com.sko4.api.ApiService;
import com.sko4.model.Details;
import com.sko4.model.Event;
import com.sko4.model.EventData;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;

/**
 * Event view.
 * Created by Mayboroda on 6/4/16.
 */
public class EventView extends RxCoordinator<EventData, EventActivity> {

    @Inject ApiService apiService;

    @Bind(R.id.event_info)          EventInfo eventInfo;
    @Bind(R.id.map_card)            MapCard mapCard;
    @Bind(R.id.desc_card)           DescCard descCard;
    @Bind(R.id.event_fab)           FloatingActionButton actionButton;
    @Bind(R.id.items_stack)         ItemStack itemStack;

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            component.inject(this);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String id = getActivity().getId();
        eventSubject.onNext(id);
    }

    @Override
    public Observable<EventData> createObservable(String value) {
        return apiService.getEventData(value);
    }

    @OnClick(R.id.event_fab)
    public void onActionButton(View view) {
        String tickets = (String) view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tickets));
        getContext().startActivity(intent);
    }

    @Override
    public void call(EventData eventData) {
        Event event = eventData.getData();
        if (event == null) {
            switcher.setDisplayedChildId(R.id.error_message);
            return;
        }
        eventInfo.bind(event);
        descCard.bind(event.getBody());
        mapCard.bind(event.getMapInfo());
        itemStack.bind(event.getArtists(), new OnClickListener() {
            @Override
            public void onClick(View view) {
                Details details = (Details) view.getTag();
                int[] screenxy = new int[2];
                view.getLocationOnScreen(screenxy);
                screenxy[0] += view.getWidth() / 2;
                DetailsActivity.startArtistsActivity(getContext(),screenxy, details);
                getActivity().overridePendingTransition(0,0);
            }
        });

        String tickets = event.getTickets();
        if (TextUtils.isEmpty(tickets)) {
            actionButton.setVisibility(GONE);
        } else {
            actionButton.setTag(tickets);
            actionButton.setVisibility(VISIBLE);
        }

        switcher.setDisplayedChildId(R.id.event_scroll);
    }
}
