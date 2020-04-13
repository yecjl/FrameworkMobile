package com.example.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    //创建一个2M大小的int数组
    private int[] datas = new int[1024 * 1024 * 2];

    /**
     * 我们创建的Handler又是一个匿名内部类的实例，其持有外部Activity的引用，这将导致了Activity无法回收，进行导致Activity持有的很多资源都无法回收，从而就造成了传说中的内存泄露问题
     */
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 模拟内存泄露，延迟5分钟发送
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // 执行代码逻辑
//            }
//        }, 1000 * 60 * 5);

        MyHandler mHandler = new MyHandler(this);
        mHandler.postDelayed(mRunnable, 1000 * 60 * 5);
    }

    /**
     * 1、使用静态内部类并继承Handler时（或者也可以单独存放成一个类文件）。因为静态的内部类不会持有外部类的引用，所以不会导致外部类实例的内存泄露
     * 2、当你需要在静态内部类中调用外部的Activity时，我们可以使用弱引用来处理
     */
    private static class MyHandler extends Handler {
        // 持有弱引用HandlerActivity, GC回收时会被回收掉.
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {
                // 执行业务逻辑
            }
            super.handleMessage(msg);
        }
    }

    private static final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //执行我们的业务逻辑
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }
}
