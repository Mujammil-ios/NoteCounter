package com.example.kgnmarketing.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.kgnmarketing.R;
import com.example.kgnmarketing.databinding.ActivityLoginBinding;
import com.example.kgnmarketing.databinding.ActivityMainBinding;
import com.example.kgnmarketing.ui.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Activity mActivity = MainActivity.this;
    private ActivityMainBinding layoutBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        layoutBinding.hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("isUserLogin");
                    editor.commit();
                    finish();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);


            }
        });
    }
}