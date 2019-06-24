package com.yesway.android.net;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import com.yesway.android.net.model.IUserModel;
import com.yesway.android.net.model.impl.UserModel;
import com.yesway.android.net.util.ConstantUtils;

import android.util.Log;

/**
 * Description: <接口服务工厂类><br>
 * Author: mxdl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class ApiManager {
  public static ApiManager apiManager;
  private Map<String, SoftReference> modelMap = new HashMap<>();

  public static ApiManager getInstance() {
    if (apiManager == null) {
      synchronized (ApiManager.class) {
        if (apiManager == null) {
          apiManager = new ApiManager();
        }
      }
    }
    return apiManager;
  }

  public void setAppKey(String key) {
    ConstantUtils.setAppkey(key);
  }
  public static class Builder{
    private String appkey;
    private String versionName;
    private int versionCode;
    private String token;
    private String userid;
    public Builder appkey(String key){
      this.appkey = key;
      return this;
    }
    public Builder versionName(String key){
      this.appkey = key;
      return this;
    }
    public Builder versionCode(int versionCode){
      this.versionCode = versionCode;
      return this;
    }
    public Builder token(String token){
      this.token = token;
      return this;
    }
    public Builder userid(String userid){
      this.userid = userid;
      return this;
    }
    public void build(){
      ApiManager.getInstance().setAppKey(appkey);
      ApiManager.getInstance().setVersionName(versionName);
      ApiManager.getInstance().setVersionCode(versionCode);
      ApiManager.getInstance().setToken(token);
      ApiManager.getInstance().setUserID(userid);
    }
  }
  public void setVersionName(String versionName) {
    ConstantUtils.setVersion(versionName);
  }

  public void setVersionCode(int versionCode) {
    ConstantUtils.setVersioncode(versionCode);
  }

  public void setToken(String token) {
    ConstantUtils.setToken(token);
  }

  public void setUserID(String userid) {
    ConstantUtils.setUserid(userid);
  }

  public IUserModel getUserModel() {
    return getModel(UserModel.class.getSimpleName(),UserModel.class);
  }

  public <T> T getModel(String key, Class<T> t) {
    T model = null;
    try {
      SoftReference softReference = modelMap.get(t.getSimpleName());
      if (softReference != null && softReference.get() != null) {
        model = (T) softReference.get();
        Log.v("MYTAG","model is cache");
      } else {
        model = t.newInstance();
        modelMap.put(t.getSimpleName(), new SoftReference(model));
        Log.v("MYTAG","model is create");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return model;
  }
}
