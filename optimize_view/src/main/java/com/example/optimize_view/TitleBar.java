package com.example.optimize_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @ClassName TitleBar
 * @Description TODO
 * @Author danke
 * @Date 2020/4/28 10:53 AM
 * @Version 1.0
 */
public class TitleBar extends LinearLayout {
    public TitleBar(Context context) {
        this(context, null, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.titlebar, this, true);
    }
}
