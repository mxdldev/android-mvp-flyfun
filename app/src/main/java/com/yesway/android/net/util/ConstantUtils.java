package com.yesway.android.net.util;

/**
 * Description: <请求头所要用的一些常量><br>
 * Author: mxdl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class ConstantUtils {
	public static String appkey;
	public static String version = "1.0.0";
	public static int versioncode;
	private static String userid;
	private static String token;

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		ConstantUtils.version = version;
	}

	public static int getVersioncode() {
		return versioncode;
	}

	public static void setVersioncode(int versioncode) {
		ConstantUtils.versioncode = versioncode;
	}

	public static String getAppkey() {
		return appkey;
	}

	public static void setAppkey(String appkey) {
		ConstantUtils.appkey = appkey;
	}

	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		ConstantUtils.userid = userid;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		ConstantUtils.token = token;
	}
}
