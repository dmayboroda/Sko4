package com.sko4;

import android.app.Application;

import com.chanel.component.AppComponent;
import com.chanel.component.DaggerAppComponent;
import com.chanel.module.ApiModule;
import com.chanel.module.AppModule;

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
