package com.yesway.android.net.dto.request;

/**
 * Description: <登陆请求实体><br>
 * Author: mxdl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
public class LoginDTO {
	private String phone;
	private String authcode;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public LoginDTO(String phone, String authcode) {
		this.phone = phone;
		this.authcode = authcode;
	}
}
