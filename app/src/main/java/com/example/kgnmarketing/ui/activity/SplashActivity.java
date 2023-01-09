package com.example.kgnmarketing.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.kgnmarketing.R;
import com.example.kgnmarketing.constant.Constant;
import com.example.kgnmarketing.databinding.ActivitySplashBinding;
import com.example.kgnmarketing.ui.MainActivity;
import com.example.kgnmarketing.utils.IntentUtils;
import com.example.kgnmarketing.utils.PreferenceUtils;
import com.example.kgnmarketing.utils.Utility;

public class SplashActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Activity mActivity = SplashActivity.this;
    private ActivitySplashBinding layoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        navigateToNext();
    }

    /**
     * navigate to next
     */
    private void navigateToNext() {
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preferences.contains("isUserLogin")) {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }, Constant.DELAY_SPLASH);
    }
}