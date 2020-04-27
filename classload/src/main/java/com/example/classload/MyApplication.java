package com.example.classload;

import android.app.Application;
import android.util.Log;

/**
 * @ClassName MyApplication
 * @Description TODO
 * @Author danke
 * @Date 2020/4/23 11:43 AM
 * @Version 1.0
 */
public class MyApplication extends Application {
    private static final String TAG = "danke";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Log.d(TAG, "###onCreate in myApplication");
            String classLoaderName = getClassLoader().getClass().getName();
            Log.d(TAG, "###onCreate in myApplication classLoaderName = " + classLoaderName);
            String parentClassLoaderName = getClassLoader().getParent().getClass().getName();
            Log.d(TAG, "###onCreate in myApplication parentClassLoaderName = " + parentClassLoaderName);
            String pParentClassLoaderName = getClassLoader().getParent().getParent().getClass().getName();
            Log.d(TAG, "###onCreate in myApplication pParentClassLoaderName = " + pParentClassLoaderName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
