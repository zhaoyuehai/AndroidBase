package com.yuehai.android.util;


import android.util.Log;

import com.yuehai.android.BuildConfig;
import com.yuehai.android.Contacts;


/**
 * LogUtil
 */
public class LogUtil {

    private static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = Contacts.LOG_TAG;

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }
}
