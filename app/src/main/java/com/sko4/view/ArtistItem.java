package com.sko4.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sko4.DetailsActivity;
import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Details;
import com.sko4.model.Style;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Artist item on event view.
 * Created by Mayboroda on 6/7/16.
 */
public class ArtistItem extends RelativeLayout {

    @Bind(R.id.artist_avatar)   ImageView avatar;
    @Bind(R.id.artist_name)     TextView name;
    @Bind(R.id.artist_style)    TextView styles;

    public ArtistItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        name.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        styles.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(Details artist) {

        final List<Style> styleList   = artist.getStyles();
        final String artistName       = artist.getName();
        final String squareUrl        = artist.getSquareUrl();

        if (styleList.isEmpty()
            && TextUtils.isEmpty(artistName)
            && TextUtils.isEmpty(squareUrl)) {
            setVisibility(GONE);
        } else {
            if (TextUtils.isEmpty(artistName)) {
                name.setVisibility(GONE);
            } else {
                name.setText(artistName);
                name.setVisibility(VISIBLE);
            }

            if (TextUtils.isEmpty(squareUrl)) {
                avatar.setVisibility(GONE);
            } else {
                Glide.with(getContext())
                        .load(squareUrl)
                        .transform(new CircleTransform(getContext()))
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(avatar);
                avatar.setVisibility(VISIBLE);
            }

            if (styleList.isEmpty()) {
                styles.setVisibility(GONE);
            } else {
                StringBuilder builder = new StringBuilder();
                for (Style style : styleList) {
                    if (builder.length() > 0) {
                        builder.append(", ");
                    }
                    builder.append(style.getName());
                }
                styles.setText(builder.toString());
                styles.setVisibility(VISIBLE);
            }

            final String id = artist.getId();
            if (!TextUtils.isEmpty(id)) {
                setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int[] screenxy = new int[2];
                        view.getLocationOnScreen(screenxy);
                        screenxy[0] += view.getWidth() / 2;
                        DetailsActivity.startArtistsActivity(getContext(),screenxy, id, artistName);
                        ((AppCompatActivity)getContext()).overridePendingTransition(0,0);
                    }
                });
            }
        }

    }
}
