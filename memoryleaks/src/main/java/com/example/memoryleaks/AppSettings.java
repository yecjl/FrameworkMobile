package com.example.memoryleaks;

import android.content.Context;

/**
 * @ClassName AppSettings
 * @Description TODO
 * @Author danke
 * @Date 2020/4/14 4:18 PM
 * @Version 1.0
 */
public class AppSettings {
    private Context mAppContext;
    private static AppSettings mAppSettings = new AppSettings();

    private AppSettings() {
    }

    public static AppSettings getInstance() {
        return mAppSettings;
    }

//    public final void setup(Context context) {
//        mAppContext = context;
//    }

    public final void setup(Context context) {
        mAppContext = context.getApplicationContext();
    }
}
