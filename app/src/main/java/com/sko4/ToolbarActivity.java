package com.sko4;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

    private Drawable share;
    private Drawable upArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        primary = ContextCompat.getColor(this, R.color.primary);
        primaryDark = ContextCompat.getColor(this, R.color.primaryDark);
        accent = ContextCompat.getColor(this, R.color.accent);
        transparent = ContextCompat.getColor(this, android.R.color.transparent);
        upArrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_black_24dp);
        share   = ContextCompat.getDrawable(this, R.drawable.ic_share_black_24dp);
        upArrow.setColorFilter(primaryDark, PorterDuff.Mode.SRC_ATOP);
        share.setColorFilter(primaryDark, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    public abstract int layoutId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.share:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        menuItem.setIcon(share);
        return true;
    }

    public abstract void share();

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
