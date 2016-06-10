package com.sko4;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.sko4.model.Event;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Activity that provides details information for event that was pressed on MainActivity's ItemsListView
 * Created by Mayboroda on 5/27/16.
 */
public class EventDetailsActivity extends BaseActivity {

    public static final String TAG = EventDetailsActivity.class.getSimpleName();

    private static final String IMAGE_EXTRA = "img_extra";
    private static final String TITLE_EXTRA = "tlt_extra";
    private static final String ID_EXTRA    = "id_extra";

    public static void navigate(AppCompatActivity activity,
                                View transitionImage,
                                Event event) {
        Intent intent = new Intent(activity, EventDetailsActivity.class);
        intent.putExtra(IMAGE_EXTRA, event.getUrl());
        intent.putExtra(TITLE_EXTRA, event.getName());
        intent.putExtra(ID_EXTRA, event.getId());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, transitionImage, IMAGE_EXTRA);
        ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
    }

    @Bind(R.id.app_bar_layout)      AppBarLayout appBarLayout;
    @Bind(R.id.collapsing_toolbar)  CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.toolbar)             Toolbar toolbar;
    @Bind(R.id.toolbar_image)       ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);
        }
        final String title = getIntent().getStringExtra(TITLE_EXTRA);
        final String url   = getIntent().getStringExtra(IMAGE_EXTRA);
        setContentView(R.layout.event_layout);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(appBarLayout, IMAGE_EXTRA);
        supportPostponeEnterTransition();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int primary = 0;
        int primaryDark = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            primary = getResources().getColor(R.color.primary, null);
            primaryDark = getResources().getColor(R.color.primaryDark, null);
        } else {
            primary = getResources().getColor(R.color.primary);
            primaryDark = getResources().getColor(R.color.primaryDark);
        }
        toolbarLayout.setTitle(title);
        toolbarLayout.setExpandedTitleColor(primary);
        toolbarLayout.setCollapsedTitleTextColor(primaryDark);
        toolbarLayout.setContentScrimColor(primary);
        toolbarLayout.setStatusBarScrimColor(primary);
        Picasso.with(this).load(url).into(image, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError() {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "unable to load image");
                }
            }
        });
    }

    public String getId() {
        return getIntent().getStringExtra(ID_EXTRA);
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
