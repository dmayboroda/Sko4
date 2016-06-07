package com.sko4.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Event;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Event description card view.
 * Created by Mayboroda on 6/6/16.
 */
public class EventDesc extends CardView{

    @Bind(R.id.event_body) TextView desc;

    public EventDesc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        desc.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(Event event) {
        String body = event.getBody();
        if (TextUtils.isEmpty(body)) {
            setVisibility(GONE);
        } else {
            desc.setText(Html.fromHtml(body
                    .trim()
                    .replace("&nbsp;", "")
                    .replace("<p>&nbsp;</p>","")));
            setVisibility(VISIBLE);
        }
    }
}
