package com.sko4;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.sko4.view.DataView;

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

    public static void startArtistsActivity(Context context, int[] coords, String id, String name) {
        startDetailsActivity(context, coords, id, true, name);
    }

    public static void startVenuesActivity(Context context, int[] coords, String id, String name){
        startDetailsActivity(context, coords, id, false, name);
    }

    private static void startDetailsActivity(Context context, int[] coords, String id,
                                             boolean isArtist, String name){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DATA_ID, id);
        intent.putExtra(IS_ARTIST, isArtist);
        intent.putExtra(SCREEN_XY, coords);
        intent.putExtra(TITLE, name);
        context.startActivity(intent);
    }

    @Bind(R.id.data_layout)         DataView dataView;
    @Bind(R.id.toolbar)             Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)  CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_venue);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name = getIntent().getStringExtra(TITLE);
        toolbar.setTitle(name);
        int primary;
        int primaryDark;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            primary = getResources().getColor(R.color.primary, null);
            primaryDark = getResources().getColor(R.color.primaryDark, null);
        } else {
            primary = getResources().getColor(R.color.primary);
            primaryDark = getResources().getColor(R.color.primaryDark);
        }
        toolbarLayout.setTitle(name);
        toolbarLayout.setExpandedTitleColor(primary);
        toolbarLayout.setCollapsedTitleTextColor(primaryDark);
        toolbarLayout.setContentScrimColor(primary);
        toolbarLayout.setStatusBarScrimColor(primary);
        if (savedInstanceState == null) {
            dataView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    dataView.getViewTreeObserver().removeOnPreDrawListener(this);
                    dataView.startAnimation();
                    return true;
                }
            });
        } else {
            dataView.finishAnimation();
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

}
