package com.kotlin.alexwan.mvpkoltin.internal.di.component;

import android.app.Activity;

import com.kotlin.alexwan.mvpkoltin.internal.di.PerActivity;
import com.kotlin.alexwan.mvpkoltin.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by alexwan on 31/07/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent{

    Activity activity();
}
