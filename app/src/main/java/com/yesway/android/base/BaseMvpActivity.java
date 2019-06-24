package com.yesway.android.base;

import android.os.Bundle;

/**
 * Description: <><br>
 * Author:      mxdl<br>
 * Date:        2018/6/11<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends BaseActivity {
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.dettach();
        }
        super.onDestroy();
    }
    public abstract T initPresenter();
}
