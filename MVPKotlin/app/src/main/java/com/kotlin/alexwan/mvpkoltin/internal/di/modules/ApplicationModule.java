package com.kotlin.alexwan.mvpkoltin.internal.di.modules;

import android.content.Context;

import com.kotlin.alexwan.mvpkoltin.KoltinApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private final KoltinApplication mApplication;

    public ApplicationModule(KoltinApplication mApplication) {
        this.mApplication = mApplication;
    }


    @Provides @Singleton
    Context provideApplicationContext(){
        return this.mApplication;
    }


}
