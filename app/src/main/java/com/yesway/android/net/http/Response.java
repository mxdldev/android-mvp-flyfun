/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2017年4月11日
 * 内容说明：
 * 
 * 编号                日期                     担当者             内容                  
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.yesway.android.net.http;

import com.yesway.android.net.dto.base.BaseHeader;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Description: <响应回调接口适配器><br>
 * Author: gxl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public abstract class Response<T extends BaseHeader> implements IResponse<T> {

    private Context mContext;

    public Response(Context context) {
        this.mContext = context;
    }

    @Override
    public void responseError(int resultcode, String errMsg) {
        if (resultcode == 1004) {
            tokenInvalid(mContext, errMsg);
            return;
        }

        if (!TextUtils.isEmpty(errMsg)) {
            Toast.makeText(mContext, errMsg, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void responseFailure(int responsecode) {
        Toast.makeText(mContext, "服务器异常，请稍后再试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestFailure() {
        Toast.makeText(mContext, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void parseJsonError() {
        Toast.makeText(mContext, "数据解析异常，请重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onFail() {

    }

    /**
     * token失效
     */
    protected void tokenInvalid(Context context, String errMsg) {

    }
}
