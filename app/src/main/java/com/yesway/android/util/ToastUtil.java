package com.yesway.android.util;

import com.yesway.android.MyApplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Description: <吐司工具类><br>
 * Author: gxl<br>
 * Date: 2018/6/11<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class ToastUtil {

    public static void showToast(String message) {
        Toast.makeText(MyApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int resid) {
        Toast.makeText(MyApplication.getInstance(), MyApplication.getInstance().getString(resid), Toast.LENGTH_SHORT)
                .show();
    }
}