package com.example.kgnmarketing.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kgnmarketing.R;
import com.example.kgnmarketing.databinding.ActivityLoginBinding;
import com.example.kgnmarketing.ui.MainActivity;
import com.example.kgnmarketing.utils.Utility;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Activity mActivity = LoginActivity.this;
    private ActivityLoginBinding layoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initListener();
    }

    /**
     * initialize listener
     */
    private void initListener() {
       layoutBinding.etUsername.getText().toString();
        layoutBinding.etUsername.getText().toString();
        layoutBinding.etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Utility.hideSoftKeyboard(mActivity);
                }
                return false;
            }
        });

        layoutBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.hideSoftKeyboard(mActivity);
                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isUserLogin", true);
                editor.putString("Username",layoutBinding.etUsername.getText().toString());
                editor.putString("Password",layoutBinding.etUsername.getText().toString());
                editor.apply();
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
