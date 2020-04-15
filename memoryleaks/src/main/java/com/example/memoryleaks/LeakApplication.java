package com.example.memoryleaks;

import android.app.Application;
import android.content.Context;

import leakcanary.AppWatcher;
import leakcanary.ObjectWatcher;

/**
 * @ClassName LeakApplication
 * @Description TODO
 * @Author danke
 * @Date 2020/4/14 11:18 PM
 * @Version 1.0
 */
public class LeakApplication extends Application {

    private ObjectWatcher watcher;

    @Override
    public void onCreate() {
        super.onCreate();
        watcher = setupLeakCanary();
    }

    private ObjectWatcher setupLeakCanary() {
        return AppWatcher.INSTANCE.getObjectWatcher();
    }

    public static ObjectWatcher getWatcher(Context context) {
        LeakApplication leakApplication = (LeakApplication) context.getApplicationContext();
        return leakApplication.watcher;
    }
}
