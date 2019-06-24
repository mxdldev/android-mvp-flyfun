package com.yesway.android.net.http;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import android.util.Log;

/**
 * Description: <Okhttp封装管理类><br>
 * Author: mxdl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class OKClientManager {

  private static final int DEFAULT_CONNECTION_TIMEOUT = 10;// 连接超时
  private static final int DEFAULT_READ_TIMEOUT = 10;// 读取超时
  private static final int DEFAULT_WRITE_TIMEOUT = 10;// 写入超时
  private static final MediaType mMediaType = MediaType.parse("application/json; charset=utf-8");// okHttp格式
  private static OKClientManager mInstance;
  private OkHttpClient mOkHttpClient;

  public static OKClientManager getInstance() {
    if (mInstance == null) {
      synchronized (OKClientManager.class) {
        if (mInstance == null) {
          mInstance = new OKClientManager();
        }
      }
    }
    return mInstance;
  }

  /**
   * 创建OkHttpClient对象
   */
  public OKClientManager() {
    mOkHttpClient = new OkHttpClient();
    mOkHttpClient.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
    mOkHttpClient.setReadTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
    mOkHttpClient.setWriteTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
  }

  /**
   * 请求方法
   *
   * @param url url地址
   * @param requestBody body
   * @param callBack 网络请求回调
   */
  public void enqueue(String url, Object requestBody, OKCallback callBack) {
    String jsonRequest = new Gson().toJson(requestBody);
    enqueue(url, jsonRequest, callBack);
  }

  /**
   * 请求方法
   *
   * @param url url地址
   * @param requestBody body
   * @param callBack 网络请求回调
   */
  public void enqueue(String url, String requestBody, OKCallback callBack) {
    Log.i("HTTP", "url>>>   " + url);
    Log.i("HTTP", "requestBody>>>   " + requestBody);
    RequestBody create = RequestBody.create(mMediaType, requestBody);
    Request request = new Request.Builder().addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json; charset=utf-8").url(url).post(create).build();
    callBack.onStart();
    mOkHttpClient.newCall(request).enqueue(callBack);
  }

  /**
   * 文件上传
   *
   * @param url
   * @param file
   * @param callback
   */
  public void uploadFile(final String url, final File file, Callback callback) {
    RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM)// 表单形式
        .addFormDataPart("file", file.getName(), RequestBody.create(null, file))// 第一个参数是服务器接收的名称，第二个是上传文件的名字，第三个是上传的文件
        .build();

    Request request = new Request.Builder().url(url).post(requestBody).build();
    mOkHttpClient.newCall(request).enqueue(callback);
  }

  /**
   * 多文件上传
   *
   * @param url
   * @param lists
   * @param callback
   */
  public void uploadFileList(final String url, List<File> lists, Callback callback) {
    MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
    for (int i = 0; i < lists.size(); i++) {
      File file = lists.get(i);
      multipartBuilder.addFormDataPart("file" + i, file.getName(), RequestBody.create(null, file));
    }

    RequestBody requestBody = multipartBuilder.build();
    Request request = new Request.Builder().url(url).post(requestBody).build();
    mOkHttpClient.newCall(request).enqueue(callback);
  }

  /**
   * 下载文件
   *
   * @param url
   * @param file
   * @param listener
   */
  public Call download(final String url, final File file,
      final DownloadCallback.OnDownloadListener listener) {
    Request request = new Request.Builder().url(url).build();
    Call call = mOkHttpClient.newCall(request);
    call.enqueue(new DownloadCallback(file, listener));
    return call;

  }

}
