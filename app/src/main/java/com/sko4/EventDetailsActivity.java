package com.sko4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sko4.model.Bindable;
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
                                Bindable bindable) {
        Intent intent = new Intent(activity, EventDetailsActivity.class);
        intent.putExtra(IMAGE_EXTRA, bindable.getUrl());
        intent.putExtra(TITLE_EXTRA, bindable.getName());
        intent.putExtra(ID_EXTRA, bindable.getId());
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
        setContentView(R.layout.event_layout);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(appBarLayout, IMAGE_EXTRA);
        supportPostponeEnterTransition();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra(TITLE_EXTRA);
        String url   = getIntent().getStringExtra(IMAGE_EXTRA);
        toolbarLayout.setTitle(title);
        toolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        Picasso.with(this).load(url).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int primaryDark = getResources().getColor(R.color.primaryDark);
                        int primary = getResources().getColor(R.color.primary);
                        toolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
                        toolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
                        supportStartPostponedEnterTransition();
                    }
                });
            }

            @Override
            public void onError() {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "unable to load image");
                }
            }
        });
    }

}
