package com.sko4.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Event description card view.
 * Created by Mayboroda on 6/6/16.
 */
public class DescCard extends CardView{

    @Bind(R.id.desc_body) TextView desc;

    public DescCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        desc.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(String body) {
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
