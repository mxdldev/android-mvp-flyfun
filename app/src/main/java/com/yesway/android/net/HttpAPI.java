package com.yesway.android.net;
/**
 * Description: <接口地址工厂类><br>
 * Author: gxl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class HttpAPI {
  /**
   * 请求地址拼接
   */
  private static String createRequestURL(String url) {
    // TODO 地址切换
    return BASE_URL_DUBUG + url;
    // return BASE_URL_RELEASE + url;
  }

  /**
   * 检证
   */
  public static final String BASE_URL_DUBUG = "http://111.207.49.76:18080/acura-mobile-gateway";
  /**
   * 号口
   */
  public static final String BASE_URL_RELEASE =
      "http://acuradbutler.yesway.cn/acura-mobile-gateway";
  /**
   * 用户登录
   */
  public static final String LOGIN_URL = createRequestURL("/mobile/user/login");
  /**
   * 获取用户信息
   */
  public static final String USER_GET_URL = createRequestURL("/mobile/user/get");

}
