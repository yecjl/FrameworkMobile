package com.example.optimize_view;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName CoordinatorLayoutActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/27 11:10 AM
 * @Version 1.0
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewStub viewstub = findViewById(R.id.viewsub);
//        viewstub.inflate();
        viewstub.setVisibility(View.VISIBLE);

        Debug.startMethodTracing("test");
        initView();
    }

    private void initView() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Debug.stopMethodTracing();
    }
}
