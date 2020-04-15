package com.example.memoryleaks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName AsyncTaskActivity
 * @Description TODO
 * @Author danke
 * @Date 2020/4/13 7:22 PM
 * @Version 1.0
 */
public class AsyncTaskActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async_task);

        button = findViewById(R.id.bt_create);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
                finish();
            }
        });
    }

    private void startAsyncTask() {
        // 这样写会导致内存泄露
//        new AsyncTask<Void, Void, Void> () {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                while (true) ;
//            }
//        };
        new MyAsyncTask().execute();
    }

    private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) ;
        }
    }
}
