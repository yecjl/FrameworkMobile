package com.example.memoryleaks;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.badoo.mobile.util.WeakHandler;

import java.lang.ref.WeakReference;

/**
 * @ClassName HandlerActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/14 3:50 PM
 * @Version 1.0
 */
public class HandlerActivity extends AppCompatActivity {
    private Button button;

    private MyHandler handler = new MyHandler(this);

    private WeakHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        button = findViewById(R.id.bt_create);

//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//            }
//        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessageDelayed(Message.obtain(), 60000);
                finish();
            }
        });
    }

    private void show() {

    }

    private static class MyHandler extends Handler {
        private final WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (mActivity != null && mActivity.get() != null) {
                mActivity.get().show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
}
