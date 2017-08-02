package com.kotlin.alexwan.mvpkoltin.internal.di.component;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.kotlin.alexwan.mvpkoltin.base.KoltinApplication;
import com.kotlin.alexwan.mvpkoltin.internal.di.modules.ApplicationModule;
import com.kotlin.alexwan.mvpkoltin.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by alexwan on 31/07/2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class , AndroidInjectionModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    void inject(KoltinApplication application);

    Context context();

    InputMethodManager inputMethodManager();
}
