package com.sko4.di;

import android.content.Context;

import com.chanel.Cycles;
import com.chanel.Lifecycle;
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

    private Picasso.Listener listener;

    public EventsModule(Picasso.Listener listener) {
        this.listener = listener;
    }

    @Provides @Lifecycle(Cycles.VIEW)
    ApiService provideApiService(RestAdapter adapter) {
        return adapter.create(ApiService.class);
    }

    @Provides @Lifecycle(Cycles.VIEW)
    Picasso providePicasso(Context context, OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(client))
                .listener(listener)
                .build();
    }

}
