/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarStore Project
 *                http://www.yesway.cn/
 * 创建时间：2016/7/02
 * 内容说明：公共的fragment基类
 * 
 * 编号		日期			担当者		内容                  
 * -------------------------------------------------
 *
 * 
 *--------------------------------------------------*/
package com.yesway.android.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description: <公共的Fragment基类><br>
 * Author: mxdl<br>
 * Date: 2018/6/6<br>
 * Version: V1.0.0<br>
 * Update: <br>
 *
 */
public abstract class BaseFragment extends Fragment {
  public static final String TAG = "YESWAY";
  protected Activity mContext;
  public View mView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = initView(inflater);
    return mView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initData(savedInstanceState);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  public <M extends View> M findView(View view, int id) {
    return (M) view.findViewById(id);
  }

  public abstract View initView(LayoutInflater inflater);
  public abstract void initData(Bundle savedInstanceState);

  @Override
  public void setMenuVisibility(boolean menuVisible) {
    super.setMenuVisibility(menuVisible);
    if (getView() != null) {
      getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
  }
}
