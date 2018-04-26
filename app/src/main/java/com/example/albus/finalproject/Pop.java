package com.example.albus.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

class Pop extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
        DisplayMetrics rule = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(rule);
        int width = rule.widthPixels;
        int height = rule.heightPixels;
        getWindow().setLayout((int) (width * 0.8),(int) (height * .6));
    }
}
