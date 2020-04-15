package com.example.memoryleaks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName StaticViewActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/14 4:28 PM
 * @Version 1.0
 */
public class StaticViewActivity extends AppCompatActivity {

    private static Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        button = findViewById(R.id.bt_create);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        button = null;
        super.onDestroy();
    }
}
