package com.sko4.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.sko4.DetailsActivity;
import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Details;
import com.sko4.model.Event;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Facebook, web, tickets and date info.
 * Created by Mayboroda on 6/4/16.
 */
public class EventInfo extends CardView {

    @Bind(R.id.event_prices)  TextView price;
    @Bind(R.id.event_web)     TextView web;
    @Bind(R.id.event_fb)      TextView fb;
    @Bind(R.id.event_date)    TextView date;
    @Bind(R.id.event_venue)   TextView venue;

    public EventInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        price.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        web.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        fb.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        date.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        venue.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(final Event eventData) {
        if (eventData.isInfoDisable()) {
            setVisibility(GONE);
            return;
        } else {
            setVisibility(VISIBLE);
        }

        String facebook = eventData.getFacebook();
        String url      = eventData.getWeb();
        String prices   = eventData.getPrice();
        String venues   = eventData.getVenue();

        DateTime startDate  = eventData.getStartDate();
        DateTime finishDate = eventData.getFinishDate();

        String start  = startDate.toString("dd.MM.yyyy");
        String finish = finishDate.toString("dd.MM.yyyy");

        final String id       = eventData.getVenueId();
        final String name     = eventData.getName();
        final Details details = new Details(name, id);
        if (!TextUtils.isEmpty(venues)) {
            venue.setVisibility(VISIBLE);
            venue.setText(venues);
            venue.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int[] screenxy = new int[2];
                    view.getLocationOnScreen(screenxy);
                    screenxy[0] += view.getWidth() / 2;
                    DetailsActivity.startVenuesActivity(getContext(), screenxy, details);
                    ((AppCompatActivity)getContext()).overridePendingTransition(0,0);
                }
            });
        } else {
            venue.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(facebook)) {
            fb.setVisibility(VISIBLE);
            fb.setTag(facebook);
        } else {
            fb.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(url)) {
            web.setVisibility(VISIBLE);
            web.setTag(url);
        } else {
            web.setVisibility(GONE);
        }

        String free = getContext().getResources().getString(R.string.free);
        if (!TextUtils.isEmpty(prices)) {
            price.setVisibility(VISIBLE);
            price.setText(prices.replace("Free", free));
        } else {
            price.setVisibility(GONE);
        }

        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(start)) {
            builder.append(start);
        }
        if (!TextUtils.isEmpty(finish)) {
            if (builder.length() > 0) {
                builder.append(" - ");
            }
            builder.append(finish);
        }
        String dates = builder.toString();
        if (!TextUtils.isEmpty(dates)) {
            date.setText(dates);
            date.setVisibility(VISIBLE);
        } else {
            date.setVisibility(GONE);
        }
    }

    @OnClick({R.id.event_fb, R.id.event_web})
    public void onUrlClick(View view) {
        String url = (String) view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        getContext().startActivity(intent);
    }
}
