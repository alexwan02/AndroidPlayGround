package com.kotlin.alexwan.mvpkoltin.internal.di.component;

import android.app.Activity;
import android.content.Context;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexwan on 31/07/2017.
 */
@Singleton
@Component(modules = ApplicationComponent.class)
public interface ApplicationComponent {
    void inject(Activity activity);

    Context context();

}
