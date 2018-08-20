package com.yesway.android.net.dto.response;


import com.yesway.android.net.dto.base.BaseHeader;
import com.yesway.android.net.dto.entity.UserInfo;

/**
 * Description: <登录响应实体><br>
 * Author:      gxl<br>
 * Date:        2018/6/6<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class LoginResponse extends BaseHeader {
    private Data data;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data{
        private String token;
        private UserInfo userinfo;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfo getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfo userinfo) {
            this.userinfo = userinfo;
        }
    }
}
