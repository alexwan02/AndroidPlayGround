package com.kotlin.alexwan.mvpkoltin.internal.di.component;

import android.app.Activity;
import android.content.Context;

import com.kotlin.alexwan.mvpkoltin.internal.di.modules.ApplicationModule;
import com.kotlin.alexwan.mvpkoltin.view.activity.BaseActivity;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexwan on 31/07/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    Context context();

}
