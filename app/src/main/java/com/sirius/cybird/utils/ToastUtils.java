package com.sirius.cybird.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.sirius.cybird.CybirdApplication;

/**
 * ToastUtils,土司辅助类
 *
 * @date: 2016-12-17
 */
public class ToastUtils {
    protected static Toast toast = null;
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void show(final String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(CybirdApplication.Companion.getContext(), s, Toast.LENGTH_SHORT);
                    toast.show();
                    oneTime = System.currentTimeMillis();
                } else {
                    twoTime = System.currentTimeMillis();
                    if (s.equals(oldMsg)) {
                        if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                            toast.show();
                        }
                    } else {
                        oldMsg = s;
                        toast.setText(s);
                        toast.show();
                    }
                }
                oneTime = twoTime;
            }
        });
    }

    public static void show(final String s, boolean isLong) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(CybirdApplication.Companion.getContext(), s, Toast.LENGTH_LONG);
                    toast.show();
                    oneTime = System.currentTimeMillis();
                } else {
                    twoTime = System.currentTimeMillis();
                    if (s.equals(oldMsg)) {
                        if (twoTime - oneTime > Toast.LENGTH_LONG) {
                            toast.show();
                        }
                    } else {
                        oldMsg = s;
                        toast.setText(s);
                        toast.show();
                    }
                }
                oneTime = twoTime;
            }
        });
    }
}
