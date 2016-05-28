package com.chanel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope annotation for different lifecycles.
 * Created by Mayboroda on 5/24/15.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Lifecycle {
    String value() default "";
}
