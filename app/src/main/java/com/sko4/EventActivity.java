package com.sko4;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sko4.model.Details;
import com.sko4.model.Event;

import butterknife.Bind;

/**
 * Activity that provides details information for event that was pressed on MainActivity's ItemsListView
 * Created by Mayboroda on 5/27/16.
 */
public class EventActivity extends ToolbarActivity {

    public static final String TAG = EventActivity.class.getSimpleName();

    private static final String IMAGE_EXTRA = "img_extra";
    private static final String TITLE_EXTRA = "tlt_extra";
    private static final String ID_EXTRA    = "id_extra";

    public static void openEventActivity(AppCompatActivity activity,
                                         View transitionImage,
                                         Event event) {
        Intent intent = new Intent(activity, EventActivity.class);
        intent.putExtra(IMAGE_EXTRA,    event.getUrl());
        intent.putExtra(TITLE_EXTRA,    event.getName());
        intent.putExtra(ID_EXTRA,       event.getId());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, transitionImage, IMAGE_EXTRA);
        ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
    }

    public static void openEventActivity(Context activity, Details details) {
        Intent intent = new Intent(activity, EventActivity.class);
        intent.putExtra(ID_EXTRA, details.getId());
        intent.putExtra(TITLE_EXTRA, details.getTitle());
        intent.putExtra(IMAGE_EXTRA, details.getImageByPath());
        activity.startActivity(intent);
    }

    @Bind(R.id.app_bar_layout)  AppBarLayout appBarLayout;
    @Bind(R.id.toolbar_image)   ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(slide);
        }
        final String title  = getIntent().getStringExtra(TITLE_EXTRA);
        final String url    = getIntent().getStringExtra(IMAGE_EXTRA);
        ViewCompat.setTransitionName(appBarLayout, IMAGE_EXTRA);
        supportPostponeEnterTransition();
        toolbarLayout.setExpandedTitleColor(primary);
        toolbarLayout.setCollapsedTitleTextColor(primaryDark);
        toolbarLayout.setContentScrimColor(primary);
        toolbarLayout.setStatusBarScrimColor(primary);
        toolbarLayout.setTitle(title);
        Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e,
                                               String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        if (BuildConfig.DEBUG && !TextUtils.isEmpty(e.getMessage())) {
                            Log.e(TAG, e.getMessage());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }
                }).into(image);
    }

    @Override
    public int layoutId() { return R.layout.event_layout; }

    public String getId() {
        return getIntent().getStringExtra(ID_EXTRA);
    }
}
