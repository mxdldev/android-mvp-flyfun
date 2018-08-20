package com.yesway.android.user;

import com.yesway.android.R;
import com.yesway.android.base.BaseMvpActivity;
import com.yesway.android.net.dto.entity.UserInfo;
import com.yesway.android.user.presenter.UserContract;
import com.yesway.android.user.presenter.UserPresenter;

import android.os.Bundle;

/**
 * Description: <用户登录页><br>
 * Author: gxl<br>
 * Date: 2018/6/11<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class UserLoginActivity extends BaseMvpActivity<UserContract.View, UserPresenter>
    implements
      UserContract.View {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  @Override
  public void initView(Bundle savedInstanceState) {
  }

  @Override
  public void initData() {

  }

  @Override
  public UserPresenter initPresenter() {
    return new UserPresenter();
  }

  @Override
  public void showUserInfo(UserInfo userInfo) {

  }
}
