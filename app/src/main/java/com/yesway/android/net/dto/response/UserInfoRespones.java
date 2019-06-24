package com.yesway.android.net.dto.response;


import com.yesway.android.net.dto.base.BaseHeader;
import com.yesway.android.net.dto.entity.UserInfo;

/**
 * Description: <用户响应实体><br>
 * Author:      mxdl<br>
 * Date:        2018/6/6<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class UserInfoRespones extends BaseHeader {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {

        private UserInfo user;

        public UserInfo getUser() {
            return user;
        }

        public void setUser(UserInfo user) {
            this.user = user;
        }
    }

}
