package com.sko4.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Artist;
import com.sko4.model.Style;
import com.squareup.picasso.Picasso;

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

    public void bind(Artist artist, Picasso picasso) {

        List<Style> styleList   = artist.getStyles();
        String artistName       = artist.getName();
        String squareUrl        = artist.getSquareUrl();

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
                picasso.load(squareUrl)
                        .transform(new CircleTransform())
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
        }

    }
}
