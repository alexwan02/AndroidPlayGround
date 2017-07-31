package com.kotlin.alexwan.mvpkoltin;

import android.app.Application;

import com.kotlin.alexwan.mvpkoltin.internal.di.component.ApplicationComponent;

/**
 * Created by alexwan on 31/07/2017.
 */

public class KoltinApplication extends Application {
    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
