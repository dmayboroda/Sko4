package com.sko4.di.module;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.sko4.BuildConfig;
import com.sko4.Cycles;
import com.sko4.Lifecycle;
import com.sko4.Sko4;
import com.sko4.api.ApiService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Events module.
 * Created by Mayboroda.
 */
@Module
public class EventsModule {

    private static final String TAG = Sko4.class.getSimpleName();

    @Provides @Lifecycle(Cycles.VIEW)
    ApiService provideApiService(RestAdapter adapter) {
        return adapter.create(ApiService.class);
    }

    @Provides @Lifecycle(Cycles.VIEW)
    Picasso providePicasso(Context context, OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso,
                                                  Uri uri,
                                                  Exception exception) {
                        if (BuildConfig.DEBUG && !TextUtils.isEmpty(uri.toString())) {
                            Log.e(TAG, uri.toString());
                        }
                    }
                })
                .build();
    }

}
