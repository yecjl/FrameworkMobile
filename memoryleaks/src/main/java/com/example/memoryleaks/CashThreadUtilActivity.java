package com.example.memoryleaks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zmsoft.embed.concurrent.ThreadUtils;

/**
 * @ClassName CashThreadUtilActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/14 2:27 PM
 * @Version 1.0
 */
public class CashThreadUtilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async_task);
        startThreadUtil();
    }

    private void startThreadUtil() {
//        ThreadUtils.run(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(60 * 60 * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        ThreadUtils.run(new MyRunnable());
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
