package com.yesway.android.net.http;


import com.yesway.android.net.dto.base.BaseHeader;

/**
 * Created by zhangke on 2017/5/10.
 */

public interface IResponse<T extends BaseHeader> {
    /**
     * 请求开始
     */
    int CODE_REQUEST_START = 1;
    /**
     * 请求完成
     */
    int CODE_REQUEST_FINISH = 2;
    /**
     * 响应成功,返回正确结果
     */
    int CODE_RESPONSE_SUCCESS = 0;
    /**
     * 响应成功,返回异常结果
     */
    int CODE_RESPONSE_ERROR = -1;
    /**
     * 响应失败 ,response的响应码不等于200
     */
    int CODE_RESPONSE_FAILURE = -2;
    /**
     * 请求异常,一般网络断开问题
     */
    int CODE_REQUEST_FAILURE = -3;
    /**
     * 数据解析异常
     */
    int CODE_PARSE_ERROR = -4;

    /**
     * 响应成功,返回异常结果
     *
     * @param resultcode 返回码
     * @param errMsg     错误信息
     */
    void responseError(int resultcode, String errMsg);

    /**
     * 响应失败 (response的响应码不等于200)
     *
     * @param responsecode 响应码
     */
    void responseFailure(int responsecode);

    /**
     * 请求异常，一般网络断开问题
     */
    void requestFailure();

    /**
     * 数据解析异常
     */
    void parseJsonError();

    /**
     * 请求开始
     */
    void onStart();

    /**
     * 请求成功
     * @param response
     */
    void onSuccess(T response);

    /**
     * 请求失败
     */
    void onFail();

    /**
     * 请求完成
     */
    void onFinish();
}
