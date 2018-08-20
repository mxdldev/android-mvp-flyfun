package com.yesway.android.net.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Description: <APP应用环境相关><br>
 * Author: gxl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
@SuppressLint("UseValueOf")
public class EnvironmentUtils {

  /**
   * TAG
   */
  @SuppressWarnings("unused")
  private static final String TAG = "EnvironmentUtil";

  /**
   * 包命
   */
  private static String mPackageName;

  /**
   * 初始化系统环境
   *
   * @param context 系统环境上下文
   */
  public static void init(Context context) {
    // Network.init(context);
    mPackageName = context.getPackageName();
  }

  /**
   * 获取包名
   *
   * @return 包名
   */
  public static String getPackageName() {
    return mPackageName;
  }

  /**
   * 获取当前应用的版本号
   */
  public static int getAppVersionCode(Context context) {
    // 获取手机的包管理者
    PackageManager pm = context.getPackageManager();
    try {
      PackageInfo packInfo = pm.getPackageInfo(getPackageName(), 0);
      return packInfo.versionCode;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 获取当前应用的版本名称
   */
  public static String getAppVersionName(Context context) {
    // 获取手机的包管理者
    PackageManager pm = context.getPackageManager();
    try {
      PackageInfo packInfo = pm.getPackageInfo(getPackageName(), 0);
      return packInfo.versionName;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
      // 不可能发生.
      return "";
    }
  }

  /**
   * 获取应用的名称
   *
   * @return
   */
  public static String getApplicationName(Context context) {
    PackageManager packageManager = null;
    ApplicationInfo applicationInfo = null;
    try {
      packageManager = context.getPackageManager();
      applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
    } catch (NameNotFoundException e) {
      applicationInfo = null;
    }
    String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
    System.out.println(applicationName);
    return applicationName;
  }

  /**
   * 是否是Foreground进程
   *
   * @param context
   * @return
   */
  public static boolean isForeground(Context context) {
    if (context != null) {
      ActivityManager activityManager =
          (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
      List<ActivityManager.RunningAppProcessInfo> processes =
          activityManager.getRunningAppProcesses();
      for (ActivityManager.RunningAppProcessInfo processInfo : processes) {
        if (processInfo.processName.equals(context.getPackageName())) {
          if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * 返回app运行状态 1:程序在前台运行 2:程序在后台运行 3:程序未启动
   * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
   */
  public static int getAppStatus(Context context) {

    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

    // 判断程序是否在栈顶
    if (list.get(0).topActivity.getPackageName().equals(context.getPackageName())) {
      return 1;
    } else {
      // 判断程序是否在栈里
      for (ActivityManager.RunningTaskInfo info : list) {
        if (info.topActivity.getPackageName().equals(context.getPackageName())) {
          return 2;
        }
      }
      return 3;// 栈里找不到，返回3
    }
  }

  /**
   * 启动应用
   *
   * @param context
   * @param className
   */
  public static void openApp(Context context, String className) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    ComponentName cn = new ComponentName(context.getPackageName(), className);
    intent.setComponent(cn);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity(intent);
  }



  /**
   * APK相关操作
   */
  public static class Package {

    /**
     * 安装APK
     *
     * @param context
     * @param file
     */
    public static void installApp(Context context, File file) {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
      context.startActivity(intent);
    }

    /**
     * 卸载APK
     *
     * @param context
     * @param packageName
     */
    public static void unInstallApp(Context context, String packageName) {
      Uri packageUri = Uri.parse("package:" + packageName);
      Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(intent);
    }

    /**
     * 获得APK包名
     *
     * @param context
     * @param apkFile
     * @return
     */
    public static String getApkFilePackage(Context context, File apkFile) {
      PackageManager pm = context.getPackageManager();
      PackageInfo info = pm.getPackageArchiveInfo(apkFile.getPath(), PackageManager.GET_ACTIVITIES);
      if (info != null) {
        return info.applicationInfo.packageName;
      }
      return null;
    }

    /**
     * 判断是否已经安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
      if (TextUtils.isEmpty(packageName)) return false;
      try {
        context.getPackageManager().getApplicationInfo(packageName,
            PackageManager.GET_UNINSTALLED_PACKAGES);
        return true;
      } catch (NameNotFoundException e) {
        return false;
      }
    }
  }

  /**
   * 判断一个应用是否安装
   *
   * @param context
   * @param packageName
   * @return
   */
  public static boolean checkAPP(Context context, String packageName) {
    if (TextUtils.isEmpty(packageName)) return false;
    try {
      context.getPackageManager().getApplicationInfo(packageName,
          PackageManager.GET_UNINSTALLED_PACKAGES);
      return true;
    } catch (NameNotFoundException e) {
      return false;
    }
  }

  public static String getTextResources(Context mContext) {
    InputStream is =
        mContext.getClass().getClassLoader().getResourceAsStream("assets/resources.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }

  /**
   * 读取城市列表资源
   */
  public static String getCityListResources(Context mContext) {
    InputStream is =
        mContext.getClass().getClassLoader().getResourceAsStream("assets/cityresources.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }


  /**
   * 获取活动列表
   */
  public static String getActivityResources(Context mContext) {
    InputStream is =
        mContext.getClass().getClassLoader().getResourceAsStream("assets/activityresources.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }

  /**
   * 获取文件
   */
  public static String gettxtResources(Context mContext, String fileNmae) {
    InputStream is = mContext.getClass().getClassLoader().getResourceAsStream("assets/" + fileNmae);
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }

  /**
   * 获取系统语言
   *
   * @param context
   * @return
   */
  public static String getSystemLanguage(Context context) {
    Locale locale = context.getResources().getConfiguration().locale;
    String language = locale.getLanguage();
    return language;
  }

}
