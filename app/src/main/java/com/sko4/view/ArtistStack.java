package com.sko4.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Artist;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * artists stack in event view
 * Created by Mayboroda on 6/7/16.
 */
public class ArtistStack extends CardView {

    @Bind(R.id.artist_container)    LinearLayout container;
    @Bind(R.id.artists_all)         TextView showAll;

    private LayoutInflater inflater;

    public ArtistStack(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(getContext());
        showAll.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(List<Artist> artists) {
        if (artists.isEmpty()) {
            setVisibility(GONE);
            return;
        }

        int size = 3;
        if (artists.size() > size) {
            showAll.setVisibility(VISIBLE);
        } else {
            size = artists.size();
            showAll.setVisibility(GONE);
        }
        for (int i = 0; i < size; i++) {
            ArtistItem artistItem = (ArtistItem)inflater
                    .inflate(R.layout.artist_info, null);
            artistItem.bind(artists.get(i));
            container.addView(artistItem);
        }

        setVisibility(VISIBLE);
    }
}
