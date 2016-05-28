package com.chanel.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.chanel.module.ApiModule;
import com.chanel.module.AppModule;
import com.chanel.module.DbModule;

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
public interface AppComponent extends ApiComponent{
    /* Provisioning methods. */
    Context context();
    SharedPreferences preferences();
    Executor executor();

    DbComponent dbComponent(DbModule dbModule);
}
