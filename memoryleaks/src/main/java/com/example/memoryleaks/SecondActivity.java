package com.example.memoryleaks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName SecondActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/13 7:11 PM
 * @Version 1.0
 */
public class SecondActivity extends AppCompatActivity {

    private Button button;
    private static Object inner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        button = findViewById(R.id.bt_create);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInnerClass();
                finish();
            }
        });
    }

    private void createInnerClass() {
        class InnerClass {

        }

        inner = new InnerClass(); // 当点击 Button 时， 会创建非静态内部类 InnerClass 的静态实例 inner 该实例的生命周期会和应用程序一样长，并 一直 持有 SecondActivity 的引用，导致SecondActivity 无法被回收。

    }
}
