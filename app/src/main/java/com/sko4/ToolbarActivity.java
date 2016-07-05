package com.sko4;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Toolbar operations activity.
 * Created by Mayboroda on 6/30/16.
 */
public abstract class ToolbarActivity extends BaseActivity {

    @Bind(R.id.toolbar)             Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)  CollapsingToolbarLayout toolbarLayout;

    protected int primary;
    protected int primaryDark;
    protected int accent;
    protected int transparent;
    private Drawable upArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            primary = getResources().getColor(R.color.primary, null);
            primaryDark = getResources().getColor(R.color.primaryDark, null);
            accent = getResources().getColor(R.color.accent, null);
            transparent = getResources().getColor(android.R.color.transparent, null);
            upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_18dp, null);
        } else {
            primary = getResources().getColor(R.color.primary);
            primaryDark = getResources().getColor(R.color.primaryDark);
            accent = getResources().getColor(R.color.accent);
            transparent = getResources().getColor(android.R.color.transparent);
            upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_18dp);
        }
        upArrow.setColorFilter(primaryDark, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    public abstract int layoutId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
