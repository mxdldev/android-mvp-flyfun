package com.yesway.android.net.dto.base;

import com.yesway.android.net.util.ConstantUtils;

/**
 * Description: <请求数据包><br>
 * Author: gxl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class RequestDTO<T> extends BaseHeader {
  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public static <T> RequestDTO<T> newInstance(T t) {
    RequestDTO<T> requestDTO = new RequestDTO<T>();
    requestDTO.setData(t);
    requestDTO.setNtspheader(new NtspHeader
            .Builder()
            .appkey(ConstantUtils.appkey)
            .bulder());
    return requestDTO;
  }

}
