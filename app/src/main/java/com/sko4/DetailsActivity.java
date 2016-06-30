package com.sko4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.sko4.model.Details;
import com.sko4.view.DataView;
import com.sko4.view.RevealView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Artists and venue details.
 * Created by Mayboroda on 6/10/16.
 */
public class DetailsActivity extends BaseActivity {

    private static final String DATA_ID     = "data_id";
    private static final String SCREEN_XY   = "yx_array";
    private static final String IS_ARTIST   = "is_artist";
    private static final String TITLE       = "details_title";

    public static void startArtistsActivity(Context context, int[] coords, Details data) {
        startDetailsActivity(context, coords, true, data);
    }

    public static void startVenuesActivity(Context context, int[] coords, Details data){
        startDetailsActivity(context, coords, false, data);
    }

    private static void startDetailsActivity(Context context, int[] coords,
                                             boolean isArtist, Details object){
        String id   = object.getId();
        String name = object.getName();
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DATA_ID, id);
        intent.putExtra(IS_ARTIST, isArtist);
        intent.putExtra(SCREEN_XY, coords);
        intent.putExtra(TITLE, name);
        context.startActivity(intent);
    }

    @Bind(R.id.data_layout)         DataView dataView;
    @Bind(R.id.toolbar)             Toolbar toolbar;
    @Bind(R.id.reveal_view)         RevealView revealView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reveal_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        revealView.setOnRevealChange(dataView);
        if (savedInstanceState == null) {
            revealView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    revealView.getViewTreeObserver().removeOnPreDrawListener(this);
                    revealView.start(getCoords());
                    return true;
                }
            });
        } else {
            revealView.finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isArtist() {
        return getIntent().getBooleanExtra(IS_ARTIST, true);
    }

    public String getDataId() {
        return getIntent().getStringExtra(DATA_ID);
    }

    public int[] getCoords() {
        return getIntent().getIntArrayExtra(SCREEN_XY);
    }

    public String getName() { return getIntent().getStringExtra(TITLE); }

}
