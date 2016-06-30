package com.sko4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.sko4.model.Details;
import com.sko4.view.DataView;
import com.sko4.view.RevealView;

import butterknife.Bind;

/**
 * Artists and venue details.
 * Created by Mayboroda on 6/10/16.
 */
public class DetailsActivity extends ToolbarActivity {

    private static final String DATA_ID     = "data_id";
    private static final String SCREEN_XY   = "yx_array";
    private static final String IS_ARTIST   = "is_artist";
    private static final String TITLE       = "details_title";
    private static final String ABOUT       = "details_about";

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
        String sum  = object.getSum();
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(IS_ARTIST, isArtist);
        intent.putExtra(SCREEN_XY, coords);
        intent.putExtra(DATA_ID, id);
        intent.putExtra(TITLE, name);
        intent.putExtra(ABOUT, sum);
        context.startActivity(intent);
    }

    @Bind(R.id.data_layout) DataView dataView;
    @Bind(R.id.reveal_view) RevealView revealView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarLayout.setExpandedTitleColor(transparent);
        toolbarLayout.setCollapsedTitleTextColor(primaryDark);
        toolbarLayout.setContentScrimColor(primary);
        toolbarLayout.setTitle(getName());
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
    public int layoutId() { return R.layout.reveal_details; }

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

    public String getSum() { return getIntent().getStringExtra(ABOUT); }

}
