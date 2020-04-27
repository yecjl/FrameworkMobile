package com.example.classload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassLoader loader = MainActivity.class.getClassLoader();
        while (loader != null) {
            Log.d("danke", loader.toString());
            loader = loader.getParent();
        }
    }
}
