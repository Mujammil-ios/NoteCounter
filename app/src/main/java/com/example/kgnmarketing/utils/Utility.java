package com.example.kgnmarketing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Type;

public class Utility {
    public static final String TAG = "Utility";

    /**
     * get application version
     *
     * @param mContext
     * @return
     */
    public static String getPackageName(Context mContext) {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void hideSoftKeyboard(final Activity mActivity, View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(mActivity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideSoftKeyboard(mActivity, innerView);
            }
        }
    }

    /**
     * hide keyboard if visible
     *
     * @param mActivity
     */
    public static void hideSoftKeyboard(Activity mActivity) {
        try {
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            // Find the currently focused view, so we can grab the correct window token from it.
            View view = mActivity.getCurrentFocus();
            // If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(mActivity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * check is user logged in
     *//*
    public static boolean isLoggedIn() {
        if (PreferenceUtils.getInstance().isLoggedIn() && PreferenceUtils.getInstance().getUser() != null) {
            return true;
        } else {
            return false;
        }
    }*/

}
