package com.yesway.android.net.http;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.yesway.android.net.dto.base.BaseHeader;
import com.yesway.android.net.dto.base.NtspHeader;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.squareup.okhttp.Response;

public class OKCallback<T extends BaseHeader> implements Callback {

  private Class<T> mResponseClazz;// 响应体类型
  private IResponse<T> mResponse;// 响应结果监听器

  public OKCallback(Class<T> clazz, IResponse<T> response) {
    this.mResponseClazz = clazz;
    this.mResponse = response;
  }

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case IResponse.CODE_REQUEST_START:
          mResponse.onStart();
          break;
        case IResponse.CODE_RESPONSE_SUCCESS:
          T response = (T) msg.obj;
          mResponse.onSuccess(response);
          mResponse.onFinish();
          break;
        case IResponse.CODE_RESPONSE_ERROR:
          int resultcode = msg.arg1;
          String errMsg = (String) msg.obj;
          mResponse.responseError(resultcode, errMsg);
          mResponse.onFail();
          mResponse.onFinish();
          break;
        case IResponse.CODE_RESPONSE_FAILURE:
          int responsecode = msg.arg1;
          mResponse.responseFailure(responsecode);
          mResponse.onFail();
          mResponse.onFinish();
          break;
        case IResponse.CODE_REQUEST_FAILURE:
          mResponse.requestFailure();
          mResponse.onFail();
          mResponse.onFinish();
          break;
        case IResponse.CODE_PARSE_ERROR:
          mResponse.parseJsonError();
          mResponse.onFail();
          mResponse.onFinish();
          break;
        default:
          break;
      }

    }

  };

  /**
   * 请求异常：取消请求、连接异常或超时等问题导致
   */
  @Override
  public void onFailure(final Request request, final IOException e) {
    e.printStackTrace();
    Message obtainMessage = handler.obtainMessage();
    obtainMessage.what = IResponse.CODE_REQUEST_FAILURE;
    handler.sendMessage(obtainMessage);

  }

  /**
   * 请求成功处理方法
   */
  @Override
  public void onResponse(final Response response) throws IOException {
    try {

      if (response.isSuccessful()) {
        // 响应成功
        final String result = response.body().string();
        Log.i("YESWAY", "response>>>   " + result);
        T t = new Gson().fromJson(result, mResponseClazz);

        NtspHeader ntspHeader = t.getNtspheader();
        int resultcode = ntspHeader.getErrcode();
        if (resultcode == 0) {
          // 正确响应结果
          Message obtainMessage = handler.obtainMessage();
          obtainMessage.what = IResponse.CODE_RESPONSE_SUCCESS;
          obtainMessage.obj = t;
          handler.sendMessage(obtainMessage);

        } else {
          // 异常响应结果
          String message = ntspHeader.getErrmsg();
          System.out.println(message);
          Message obtainMessage = handler.obtainMessage();
          obtainMessage.what = IResponse.CODE_RESPONSE_ERROR;

          obtainMessage.arg1 = resultcode;
          obtainMessage.obj = message;
          handler.sendMessage(obtainMessage);
        }
      } else {
        // 响应失败
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = IResponse.CODE_RESPONSE_FAILURE;
        obtainMessage.arg1 = response.code();
        handler.sendMessage(obtainMessage);
      }

    } catch (Exception e) {
      e.printStackTrace();
      Message obtainMessage = handler.obtainMessage();
      obtainMessage.what = IResponse.CODE_PARSE_ERROR;
      handler.sendMessage(obtainMessage);
    }

  }

  void onStart() {
    Message obtainMessage = handler.obtainMessage();
    obtainMessage.what = IResponse.CODE_REQUEST_START;
    handler.sendMessage(obtainMessage);
  }
}
