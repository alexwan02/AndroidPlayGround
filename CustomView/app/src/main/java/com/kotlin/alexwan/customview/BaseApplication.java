package com.kotlin.alexwan.customview;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by alexwan on 28/07/2017.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(BaseApplication.class.getSimpleName(), "onCreate : pid = " + Process.myPid()
                + " ; thread = " + Thread.currentThread().getName());
    }
}
