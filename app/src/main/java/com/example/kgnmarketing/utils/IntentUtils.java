package com.example.kgnmarketing.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.kgnmarketing.R;

import java.util.List;
import java.util.Locale;

public class IntentUtils {
    private static String TAG = "IntentUtils";
    private static IntentUtils instance;

    /**
     * result code
     */
    /**
     * result code
     */
    public static final int RESULTCODE_CAMERA = 5001;
    public static final int RESULTCODE_GALLERY = 5002;
    public static final int RESULTCODE_ACCOUNT = 5003;
    public static final int RESULTCODE_BOOKING = 5003;

    private IntentUtils() {

    }

    public static IntentUtils getInstance() {
        if (instance == null) {
            instance = new IntentUtils();
        }
        return instance;
    }

    /**
     * check is activity running or not
     *
     * @param mContext
     * @return
     */
    public boolean isActivityVisible(Context mContext, String activity) {
        boolean isActivityVisible = false;
        try {
            ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                List<ActivityManager.AppTask> runningProcesses = am.getAppTasks();
                for (ActivityManager.AppTask processInfo : runningProcesses) {
                    if (processInfo.getTaskInfo().topActivity.getClassName().equalsIgnoreCase(activity)) {
                        isActivityVisible = true;
                    }
                }
            } else {
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                ComponentName componentInfo = taskInfo.get(0).topActivity;
                if (componentInfo.getClassName().equalsIgnoreCase(activity)) {
                    isActivityVisible = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Debugger.logE(TAG, activity + " visible::" + isActivityVisible);
        return isActivityVisible;
    }

    /**
     * * intent - open camera
     *
     * @param mContext
     */
    public void openCamera(Context mContext) {
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * intent - open browser
     *
     * @param mContext
     * @param link
     */
    public void openBrowser(Context mContext, String link) {
        try {
            if (link == null || TextUtils.isEmpty(link)) {
                return;
            }

            if (!link.startsWith("http://") && !link.startsWith("https://")) {
                link = "http://" + link;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(link));
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * intent - send email
     *
     * @param mContext
     * @param email
     * @param subject
     * @param body
     */
    public void sendEmail(Context mContext, String email, String subject, String body) {
        try {
            if (email == null) {
                email = "";
            }

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
            emailIntent.setAction(Intent.ACTION_SEND);
            final PackageManager pm = mContext.getPackageManager();
            final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
            ResolveInfo best = null;
            for (final ResolveInfo info : matches) {
                if (info.activityInfo.packageName.endsWith(".gm") ||
                        info.activityInfo.name.toLowerCase().contains("gmail")) {
                    best = info;
                }
            }
            if (best != null) {
                emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                mContext.startActivity(emailIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * intent - open map and draw path
     *
     * @param mContext
     * @param sourceLat
     * @param sourceLong
     * @param destinationLat
     * @param destinationLong
     * @param name
     */
    public void openMap(Context mContext, double sourceLat, double sourceLong, String destinationLat, String destinationLong, String name) {
        try {
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=17&q=loc:%f,%f,%s",
                    sourceLat,
                    sourceLong,
                    Double.parseDouble(destinationLat),
                    Double.parseDouble(destinationLong),
                    "(" + name + ")");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            mapIntent.setPackage("com.google.android.apps.maps");
            mContext.startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, mContext.getString(R.string.msg_install_google_maps), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * intent - make phone call
     *
     * @param mContext
     * @param phone
     */
    public void makePhoneCall(Context mContext, String phone) {
        try {
            if (phone == null || TextUtils.isEmpty(phone)) {
                return;
            }

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            mContext.startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * animate screen when activity open
     *
     * @param mActivity
     */
    public void transitionNext(Activity mActivity) {
        mActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * animate screen when activity close
     *
     * @param mActivity
     */
    public void transitionPrevious(Activity mActivity) {
        mActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    /**
     * page new activity
     *
     * @param mActivity
     * @param fragment
     * @param classActivity - activity name
     * @param bundle        - send data into bundle
     * @param options       - transition
     */
    public void navigateToNextActivity(Activity mActivity,
                                       Fragment fragment,
                                       Class classActivity,
                                       Bundle bundle,
                                       Bundle options) {
        Intent intent = new Intent(mActivity, classActivity);
        intent.putExtras(bundle);
        if (fragment != null) {
            fragment.startActivity(intent, options);
        } else {
            mActivity.startActivity(intent, options);
        }
        transitionNext(mActivity);
    }

    /**
     * page new activity
     *
     * @param mActivity
     * @param fragment
     * @param classActivity - activity name
     * @param bundle        - send data into bundle
     * @param options       - transition
     * @param resultCode    - activity result code
     */
    public void navigateToNextActivity(Activity mActivity,
                                       Fragment fragment,
                                       Class classActivity,
                                       Bundle bundle,
                                       Bundle options,
                                       int resultCode) {
        Intent intent = new Intent(mActivity, classActivity);
        intent.putExtras(bundle);
        if (fragment != null) {
            fragment.startActivityForResult(intent, resultCode, options);
        } else {
            mActivity.startActivityForResult(intent, resultCode, options);
        }
        transitionNext(mActivity);
    }

    /**
     * page new activity
     *
     * @param mActivity
     * @param classActivity - activity name
     * @param bundle        - send data into bundle
     * @param clearStack
     */
    public void navigateToNextActivity(Activity mActivity,
                                       Class classActivity,
                                       Bundle bundle,
                                       boolean clearStack) {
        Intent intent = new Intent(mActivity, classActivity);
        intent.putExtras(bundle);
        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        mActivity.startActivity(intent);
        transitionNext(mActivity);
    }
}
