package com.sko4.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.sko4.R;
import com.sko4.model.Media;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mayboroda on 7/4/16.
 */
public class MediaStack extends CardView {

    @Bind(R.id.media_container) LinearLayout container;
    private LayoutInflater inflater;

    public MediaStack(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(getContext());
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void bind(List<Media> medias) {
        if (medias != null && !medias.isEmpty()) {
            for (Media media : medias) {
                WebView webView = (WebView) inflater.inflate(R.layout.media_view, null);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadDataWithBaseURL("", media.getCode(), "text/html", "UTF-8", "");
                container.addView(webView);
            }
        } else {
            setVisibility(GONE);
        }
    }

}
