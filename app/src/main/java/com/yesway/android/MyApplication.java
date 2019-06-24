package com.yesway.android;

import com.yesway.android.net.ApiManager;
import com.yesway.android.net.util.EnvironmentUtils;

import android.app.Application;

/**
 * Description: <初始化应用程序><br>
 * Author:      mxdl<br>
 * Date:        2018/6/6<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyApplication extends Application {
    private static MyApplication mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        new ApiManager.Builder()
                .appkey("androida-3e1d-45c0-9353-46a12984f0d4")
                .versionName(EnvironmentUtils.getAppVersionName(getApplicationContext()))
                .versionCode(EnvironmentUtils.getAppVersionCode(getApplicationContext())).build();
    }
    public static MyApplication getInstance(){
        return mApplication;
    }
}
