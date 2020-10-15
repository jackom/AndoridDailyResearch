package com.example.testdemos.log;

import android.util.Log;

/**
 * @date：2019-05-09 12:17
 * @desc：
 * @author：jackom
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    private static ILogInterceptor logInterceptor;

    public static void v(String msg) {
        if (null != logInterceptor) {
            logInterceptor.v("v: " + msg);
        }
        Log.v(TAG, msg);
    }

    public static void i(String msg) {
        if (null != logInterceptor) {
            logInterceptor.i("i: " + msg);
        }
        Log.i(TAG, msg);
    }

    public static void w(String msg) {
        if (null != logInterceptor) {
            logInterceptor.w("w: " + msg);
        }
        Log.w(TAG, msg);
    }

    public static void d(String msg) {
        if (null != logInterceptor) {
            logInterceptor.d("d: " + msg);
        }
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (null != logInterceptor) {
            logInterceptor.e("e: " + msg);
        }
        Log.e(TAG, msg);
    }

    public static void setLogInterceptor(ILogInterceptor iLogInterceptor) {
        logInterceptor = iLogInterceptor;
    }
}
