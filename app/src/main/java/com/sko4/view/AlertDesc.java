package com.sko4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Event description alert.
 * Created by Mayboroda on 6/15/16.
 */
public class AlertDesc extends ScrollView{

    @Bind(R.id.full_desc) TextView desc;

    public AlertDesc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        desc.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(String text) {
        desc.setText(text);
    }
}
