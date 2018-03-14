package cn.ltaoj.mythguard.ui.activity;

import android.view.View;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.mvp.presenter.RegistPresenter;
import cn.ltaoj.mythguard.mvp.view.IRegistView;

/**
 * Created by ltaoj on 2018/3/14 17:09.
 */

public class RegistActivity extends MVPBaseActivity<IRegistView, RegistPresenter> implements IRegistView {
    private static final String TAG = "RegistActivity";

    private final int layoutId = R.layout.activity_regist;

    // 表示当前注册进度，默认为第一步
    private int step = 1;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected RegistPresenter createPresenter() {
        return new RegistPresenter(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void prevFragment() {

    }

    @Override
    public void nextFragment() {

    }

    @Override
    public void jumpToLogin() {
        jumpToActivity(LoginActivity.class, true, null);
    }
}
