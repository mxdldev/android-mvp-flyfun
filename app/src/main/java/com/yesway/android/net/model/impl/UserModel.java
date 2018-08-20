package com.yesway.android.net.model.impl;


import com.yesway.android.net.HttpAPI;
import com.yesway.android.net.dto.base.BaseHeader;
import com.yesway.android.net.dto.base.RequestDTO;
import com.yesway.android.net.dto.request.LoginDTO;
import com.yesway.android.net.dto.response.LoginResponse;
import com.yesway.android.net.dto.response.UserInfoRespones;
import com.yesway.android.net.http.IResponse;
import com.yesway.android.net.http.OKCallback;
import com.yesway.android.net.http.OKClientManager;
import com.yesway.android.net.model.IUserModel;

/**
 * 用户服务
 */
public class UserModel implements IUserModel {

    @Override
    public void login(String phone, String verifyCode, IResponse<LoginResponse> response) {
        RequestDTO<LoginDTO> requestBean = RequestDTO.newInstance(new LoginDTO(phone, verifyCode));
        OKCallback callback = new OKCallback<LoginResponse>(LoginResponse.class, response);
        OKClientManager.getInstance().enqueue(HttpAPI.LOGIN_URL, requestBean, callback);
    }

       @Override
    public void getUserInfo(IResponse response) {
        BaseHeader baseHeader = BaseHeader.newInstance();
        OKCallback callback = new OKCallback<UserInfoRespones>(UserInfoRespones.class, response);
        OKClientManager.getInstance().enqueue(HttpAPI.USER_GET_URL, baseHeader, callback);
    }
}
