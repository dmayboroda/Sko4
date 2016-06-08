package com.sko4;

import android.app.Application;

import com.sko4.di.component.AppComponent;
import com.sko4.di.component.DaggerAppComponent;
import com.sko4.di.module.ApiModule;
import com.sko4.di.module.AppModule;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Application context.
 * Created by Mayboroda.
 */
public class Sko4 extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule("http://sko4.com"))
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

}
