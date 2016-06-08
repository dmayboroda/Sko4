package com.sko4.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.sko4.di.module.ApiModule;
import com.sko4.di.module.AppModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application component. Dagger main component.
 * Created by Mayboroda on 5/23/15.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent extends ApiComponent {
    /* Provisioning methods. */
    Context context();
    SharedPreferences preferences();
    Executor executor();

}
