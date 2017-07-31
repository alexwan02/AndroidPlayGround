package com.kotlin.alexwan.mvpkoltin.internal.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by alexwan on 31/07/2017.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
