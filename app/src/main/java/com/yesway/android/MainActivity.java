package com.yesway.android;

import com.yesway.android.base.BaseActivity;
import com.yesway.android.base.BaseMvpActivity;
import com.yesway.android.net.ApiManager;
import com.yesway.android.net.dto.response.LoginResponse;
import com.yesway.android.net.http.Response;
import com.yesway.android.net.model.IUserModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends BaseActivity {

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.second_activity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
//                IUserModel userModel = ApiManager.getInstance().getUserModel();
//
//                userModel.login("13621310260", "123", new Response<LoginResponse>(MainActivity.this) {
//                    @Override
//                    public void onSuccess(LoginResponse response) {
//                        Log.v("MYTAG","onSuccess...");
//                        LoginResponse.Data data = response.getData();
//                    }
//
//                    @Override
//                    public void onStart() {
//                        Log.v("MYTAG","onStart...");
//                    }
//
//                    @Override
//                    public void onFail() {
//                        Log.v("MYTAG","onFail...");
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        Log.v("MYTAG","onFinish...");
//                    }
//                });
            }
        });
        
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main_meun,menu);
      return super.onCreateOptionsMenu(menu);
    }
}
