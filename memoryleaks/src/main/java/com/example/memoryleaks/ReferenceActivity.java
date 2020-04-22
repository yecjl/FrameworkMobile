package com.example.memoryleaks;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @ClassName ReferenceActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/15 3:18 PM
 * @Version 1.0
 */
public class ReferenceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity mActivity = this;
        ReferenceQueue<Activity> mQueue = new ReferenceQueue<>();
        WeakReference<Activity> mWeakReference = new WeakReference<>(mActivity, mQueue);
    }
}
