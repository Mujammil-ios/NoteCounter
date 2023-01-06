package com.example.kgnmarketing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

public class PreferenceUtils {
    private static String TAG = "PreferenceUtils";
    private static PreferenceUtils instance;
    private static Context mContext;
    private Activity activity;


//    private String PREFERENCE_LOCALE = getPackageName(getAppContext());
    private String PREFERENCE_LOCALE = "kgn";

    // for app
    private String preference_fcmToken = "fcmToken";
    private String preference_authToken = "authToken";
    private String preference_username = "username";
    private String preference_password = "password";
    private String preference_isLoggedIn = "isLoggedIn";

    private String preference_userModel = "userModel";

    // shared preferences
    private SharedPreferences sp_locale;

    private PreferenceUtils() {
        sp_locale = activity.getSharedPreferences(PREFERENCE_LOCALE, Context.MODE_PRIVATE);
    }

    public static Context getAppContext() {
        return mContext;
    }
    public static PreferenceUtils getInstance() {
        if (instance == null) {
            instance = new PreferenceUtils();
        }
        return instance;
    }
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
    /**
     * clear local storage
     */
    public void clearData() {
        sp_locale.edit().clear().commit();
    }

    /**
     * store data into local
     */

    // token
    public String getFcmToken() {
        return sp_locale.getString(preference_fcmToken, "");
    }

    public void setFcmToken(String token) {
        sp_locale.edit().putString(preference_fcmToken, token).commit();
    }

    public String getAuthToken() {
        return sp_locale.getString(preference_authToken, "");
    }

    public void setAuthToken(String token) {
        sp_locale.edit().putString(preference_authToken, token).commit();
    }

    // store - is user logged in or not
    public boolean isLoggedIn() {
        return sp_locale.getBoolean(preference_isLoggedIn, false);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sp_locale.edit().putBoolean(preference_isLoggedIn, isLoggedIn).commit();
    }

    // store - user credential
    public String getUsername() {
        return sp_locale.getString(preference_username, "");
    }

    public String getPassword() {
        return sp_locale.getString(preference_password, "");
    }

    public void setUserCredential(String username, String password) {
        sp_locale.edit().putString(preference_username, username)
                .putString(preference_password, password).commit();
    }

    /*// store - logged in user's data
    public UserModel getUser() {
        return (UserModel) Utility.jsonToPojo(sp_locale.getString(preference_userModel, null), UserModel.class);
    }

    public void setUser(UserModel object) {
        sp_locale.edit().putString(preference_userModel, Utility.pojoToJson(object)).commit();
    }*/
}
