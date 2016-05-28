package com.chanel.component;

import com.chanel.AppComponentInjectionTest;
import com.chanel.module.ApiModule;
import com.chanel.module.AppModule;
import com.chanel.module.DbModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        DbModule.class,
        ApiModule.class
})
public interface TestAppComponent {
    void inject(AppComponentInjectionTest activity);
}
