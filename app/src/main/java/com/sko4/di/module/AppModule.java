package com.sko4.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application module.
 * Created by Mayboroda on 5/23/15.
 */
@Module
public final class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    Context provideContext() { return context; }

    @Provides @Singleton
    SharedPreferences providePreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Provides @Singleton
    Executor provideExecutor() { return Executors.newCachedThreadPool(); }
}
