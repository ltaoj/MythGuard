package cn.ltaoj.mythguard.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.mvp.presenter.RegistPresenter;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.ui.fragment.RegistOneFragment;
import cn.ltaoj.mythguard.ui.fragment.RegistThrFragment;
import cn.ltaoj.mythguard.ui.fragment.RegistTwoFragment;

/**
 * Created by ltaoj on 2018/3/14 17:09.
 */

public class RegistActivity extends MVPBaseActivity<IRegistView, RegistPresenter> implements IRegistView {
    private static final String TAG = "RegistActivity";

    private final int layoutId = R.layout.activity_regist;

    // 表示当前注册进度，默认为第一步
    private int step = 0;

    private ProgressBar mProgressBar;
    private List<MVPBaseFragment> fragments = new ArrayList<>();

    @Override
    protected void setSystemUI() {
        hideSystemUI();
    }

    private void hideSystemUI() {
        int visibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(visibility);
    }

    private void showSystemUI() {
        int visibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }


    @Override
    protected void initView() {
        mProgressBar = findViewById(R.id.progress_bar);
        initFragments();
        getSupportFragmentManager().beginTransaction() // 将第一个Fragment添加到栈中
                .add(R.id.regist_steps_layout, fragments.get(step), String.valueOf(step))
                .addToBackStack(String.valueOf(step))
                .commit();
    }

    private void initFragments() {
        RegistOneFragment registOneFragment = new RegistOneFragment();
        RegistTwoFragment registTwoFragment = new RegistTwoFragment();
        RegistThrFragment registThrFragment = new RegistThrFragment();
        registOneFragment.setRegistView(this);
        registTwoFragment.setRegistView(this);
        registThrFragment.setRegistView(this);
        fragments.add(registOneFragment);
        fragments.add(registTwoFragment);
        fragments.add(registThrFragment);
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
        if (step == 0) {
            finish();
            return;
        }

        getSupportFragmentManager().popBackStack(String.valueOf(--step), 0);

        mProgressBar.setProgress((step * 100)/fragments.size());
    }

    @Override
    public void nextFragment() {
        if (step == fragments.size() - 1) {
//            jumpToLogin();
            // 因为从登陆界面跳转到注册界面，之前的activity还存在，直接关掉当前界面即可
            finish();
            return;
        }

        // 更换fragment，同时设置进度条
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.regist_steps_layout, fragments.get(++step), String.valueOf(step))
                .addToBackStack(String.valueOf(step))
                .commit();

        mProgressBar.setProgress((step * 100)/fragments.size());
    }

    @Override
    public void jumpToLogin() {
        jumpToActivity(LoginActivity.class, true, null);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onBackPressed() {
        prevFragment();
    }
}
