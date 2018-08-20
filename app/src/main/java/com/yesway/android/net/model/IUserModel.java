package com.yesway.android.net.model;

import com.yesway.android.net.dto.response.LoginResponse;
import com.yesway.android.net.http.IResponse;

/**
 * 用户服务
 *
 * @author weiz
 */
public interface IUserModel {
    void login(String phone, String verifyCode, IResponse<LoginResponse> response);
    void getUserInfo(IResponse response);
}
