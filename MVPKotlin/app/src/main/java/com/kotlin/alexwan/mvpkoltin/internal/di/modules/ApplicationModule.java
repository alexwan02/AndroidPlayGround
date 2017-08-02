package com.kotlin.alexwan.mvpkoltin.internal.di.modules;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.inputmethod.InputMethodManager;

import com.kotlin.alexwan.mvpkoltin.base.KoltinApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule{
    private final KoltinApplication mApplication;

    public ApplicationModule(KoltinApplication mApplication) {
        this.mApplication = mApplication;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }

    @Provides
    @Singleton
    InputMethodManager provideInputMethodManager() {
        return (InputMethodManager) mApplication.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    @Singleton
    WifiManager provideWifiManager(){
        return (WifiManager) mApplication.getSystemService(Context.WIFI_SERVICE);
    }
}
