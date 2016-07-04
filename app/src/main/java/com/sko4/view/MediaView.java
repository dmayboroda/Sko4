package com.sko4.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.sko4.R;
import com.sko4.model.Media;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mayboroda on 7/4/16.
 */
public class MediaView extends CardView {

    @Bind(R.id.web_media_view) WebView webView;

    public MediaView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void bind(Media media) {
          webView.loadDataWithBaseURL("", media.getCode(), "text/html", "UTF-8", "");
    }

}
