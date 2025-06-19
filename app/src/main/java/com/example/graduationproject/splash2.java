package com.example.graduationproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash2 extends Activity {

    private static final int SPLASH_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash2.this, login.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}
