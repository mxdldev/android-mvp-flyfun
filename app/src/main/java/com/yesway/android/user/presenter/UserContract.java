package com.yesway.android.user.presenter;

import com.yesway.android.base.IBaseView;
import com.yesway.android.net.dto.entity.UserInfo;

/**
 * Description: <><br>
 * Author:      mxdl<br>
 * Date:        2018/6/11<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface UserContract {
    interface View extends IBaseView {
        void showUserInfo(UserInfo userInfo);
    }
    interface Presenter{
        void login(String username, String pwd);
    }
}
