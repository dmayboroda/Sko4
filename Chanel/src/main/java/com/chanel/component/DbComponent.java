package com.chanel.component;

import com.chanel.module.DbModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Database component
 *
 * Created by a-petruk on 23.06.15.
 */
@Singleton
@Subcomponent(modules = DbModule.class)
public interface DbComponent {
    /* Used as subcomponent only. */
}
