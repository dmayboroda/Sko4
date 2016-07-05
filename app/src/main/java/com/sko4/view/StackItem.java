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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Artist item on event view.
 * Created by Mayboroda on 6/7/16.
 */
public class StackItem extends RelativeLayout {

    @Bind(R.id.item_avatar)     ImageView avatar;
    @Bind(R.id.item_name)       TextView name;
    @Bind(R.id.item_details)    TextView plus;

    private boolean isArtist = false;

    public StackItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        name.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        plus.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void setArtistEnable(boolean isArtist) {
        this.isArtist = isArtist;
    }

    public void bind(final Details details) {

        String additional = details.getAdditional();
        String title      = details.getTitle();
        String squareUrl  = details.getImageByPath();

        if (TextUtils.isEmpty(additional)
            && TextUtils.isEmpty(title)
            && TextUtils.isEmpty(squareUrl)) {
            setVisibility(GONE);
        } else {
            if (TextUtils.isEmpty(title)) {
                name.setVisibility(GONE);
            } else {
                name.setText(title);
            }

            if (TextUtils.isEmpty(squareUrl)) {
                avatar.setVisibility(GONE);
            } else {
                Glide.with(getContext())
                        .load(squareUrl)
                        .transform(new CircleTransform(getContext()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(avatar);
            }

            if (TextUtils.isEmpty(additional)) {
                plus.setVisibility(GONE);
            } else {
                plus.setText(additional);
            }

            final String id = details.getId();
            if (!TextUtils.isEmpty(id)) {
                setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int[] screenxy = new int[2];
                        view.getLocationOnScreen(screenxy);
                        screenxy[0] += view.getWidth() / 2;
                        if (isArtist) {
                            DetailsActivity.startArtistsActivity(getContext(),screenxy, details);
                            ((AppCompatActivity)getContext()).overridePendingTransition(0,0);
                        } else {
                            //start event activity
                        }
                    }
                });
            }
        }

    }
}
