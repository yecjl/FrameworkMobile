package com.example.memoryleaks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import leakcanary.ObjectWatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    //    class LeakThread extends Thread {  // 这样写会导致内存泄露
    private static class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
//        CleanLeakUtils.fixInputMethodManagerLeak(this);
        ObjectWatcher watcher = LeakApplication.getWatcher(this);
        watcher.watch(this, "MainActivity");
        super.onDestroy();
    }
}
