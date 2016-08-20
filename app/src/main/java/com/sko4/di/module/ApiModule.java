package com.sko4.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.sko4.BuildConfig;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Api connection module.
 * Created by Mayboroda on 5/23/15.
 */
@Module
public final class ApiModule {

    /** 50MB cache size. */
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    /** requests timeout. */
    private static final int TIMEOUT = 10;
    /** Base request url.*/
    private String url;

    public ApiModule(String url) {
        this.url = url;
    }


    @Provides @Singleton
    RestAdapter provideRestAdapter(Converter converter,
                                   Endpoint endpoint,
                                   Executor executor,
                                   OkHttpClient client) {
        return new RestAdapter.Builder()
                .setLogLevel(BuildConfig.DEBUG
                        ? RestAdapter.LogLevel.FULL
                        : RestAdapter.LogLevel.NONE)
                .setClient(new OkClient(client))
                .setConverter(converter)
                .setEndpoint(endpoint)
                .setExecutors(executor, executor)
                .build();
    }

    @Provides @Singleton
    Endpoint provideEndpoint() { return Endpoints.newFixedEndpoint(url); }

    @Provides @Singleton
    Converter provideConverter() { return new GsonConverter(new Gson()); }

    @Provides @Singleton
    OkHttpClient provideHttpClient(Context context){
        return createHttpClient(context);
    }

    static OkHttpClient createHttpClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(TIMEOUT, TimeUnit.SECONDS);
        File cacheDir = new File(context.getCacheDir(), "cached");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        okHttpClient.setCache(cache);
        return okHttpClient;
    }


}