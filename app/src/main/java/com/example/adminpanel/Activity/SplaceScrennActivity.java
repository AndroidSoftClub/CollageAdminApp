package com.example.adminpanel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.adminpanel.R;

public class SplaceScrennActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splacescreen_layout);

        new Handler(getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplaceScrennActivity.this,MainActivity.class));
                        finish();
                    }
                },1000);

    }
}