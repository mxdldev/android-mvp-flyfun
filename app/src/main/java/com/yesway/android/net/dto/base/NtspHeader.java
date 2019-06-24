package com.yesway.android.net.dto.base;

/**
 * Description: <车联网传输协议头><br>
 * <ul>
 * <li>1.请求头、相应头都会携带该信息</li>
 * </ul>
 * Author: mxdl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class NtspHeader {
  private String userid;// 用户id
  private String token;// 访问令牌
  private String timestamp;// 访问时间戳
  private String appkey;// app的key
  private String versionname;// app的版本名字
  private int versioncode;// app的版本号
  private int errcode;// 响应错误码
  private String errmsg;// 响应错误信息
  public NtspHeader(Builder builder){
      this.userid = builder.userid;// 用户id
      this.token = builder.token;// 访问令牌
      this.timestamp = builder.timestamp;// 访问时间戳
      this.appkey = builder.appkey;// app的key
      this.versionname = builder.versionname;// app的版本名字
      this.versioncode = builder.versioncode;// app的版本号
      this.errcode = builder.errcode;// 响应错误码
      this.errmsg = builder.errmsg;// 响应错误信息
  }
  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getAppkey() {
    return appkey;
  }

  public void setAppkey(String appkey) {
    this.appkey = appkey;
  }

  public String getVersionname() {
    return versionname;
  }

  public void setVersionname(String versionname) {
    this.versionname = versionname;
  }

  public int getVersioncode() {
    return versioncode;
  }

  public void setVersioncode(int versioncode) {
    this.versioncode = versioncode;
  }

  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
    public static class Builder {
        private String userid;// 用户id
        private String token;// 访问令牌
        private String timestamp;// 访问时间戳
        private String appkey;// app的key
        private String versionname;// app的版本名字
        private int versioncode;// app的版本号
        private int errcode;// 响应错误码
        private String errmsg;// 响应错误信息
        public Builder userid(String userid){
            this.userid = userid;
            return this;
        }
        public Builder token(String token){
            this.token = token;
            return this;
        }
        public Builder timestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }
        public Builder appkey(String appkey){
            this.appkey = appkey;
            return this;
        }

        public Builder versionname(String versionname){
            this.versionname = versionname;
            return this;
        }
        public Builder versioncode(int versioncode){
            this.versioncode = versioncode;
            return this;
        }
        public Builder errcode(int errcode){
            this.errcode = errcode;
            return this;
        }
        public Builder errmsg(String errmsg){
            this.errmsg = errmsg;
            return this;
        }
        public NtspHeader bulder(){
            return new NtspHeader(this);
        }
    }
}
