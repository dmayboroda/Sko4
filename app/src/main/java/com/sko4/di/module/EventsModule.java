package com.sko4.di.module;

import com.sko4.Cycles;
import com.sko4.Lifecycle;
import com.sko4.Sko4;
import com.sko4.api.ApiService;

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

}
