package cn.ltaoj.mythguard.ui.activity;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.ltaoj.mythguard.MainActivity;
import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.mvp.presenter.LoginPresenter;
import cn.ltaoj.mythguard.mvp.view.ILoginView;
import cn.ltaoj.mythguard.util.ToastUtil;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Created by ltaoj on 2018/3/13 14:32.
 */

public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {
    private static final String TAG = "LoginActivity";

    private final int layoutId = R.layout.activity_login;

    private EditText accountInput;
    private EditText pwdInput;

    @Override
    protected void initView() {
        accountInput = findViewById(R.id.et_account);
        pwdInput = findViewById(R.id.et_pwd);

        findViewById(R.id.bt_family).setOnClickListener(this);
        findViewById(R.id.bt_visitor).setOnClickListener(this);
        findViewById(R.id.tv_forget).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        // 尝试自动登录
        mPresenter.autoLogin();
    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: login");
        switch (v.getId()) {
            case R.id.bt_family:
            case R.id.bt_visitor:
                Log.i(TAG, "onClick: login");
                mPresenter.login();
                break;
            case R.id.tv_forget:
                jumpToRegist();
                break;

        }
    }

    @Override
    public String getAccount() {
        return accountInput.getText().toString();
    }

    @Override
    public String getPwd() {
        return pwdInput.getText().toString();
    }

    @Override
    public void showTip(InputType inputType) {
        String tipText = null;
        if (inputType == InputType.ACCOUNT) {
            tipText = "账号为空，请输入账号再登录";
            accountInput.requestFocus();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                accountInput.setError(tipText);
            } else {
                ToastUtil.showToast(this, tipText);
            }
        } else {
            tipText = "密码为空，请输入密码再登录";
            pwdInput.requestFocus();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                pwdInput.setError(tipText);
            } else {
                ToastUtil.showToast(this, tipText);
            }
        }
    }

    @Override
    public void hideTip() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            accountInput.setError(null);
            pwdInput.setError(null);
        }
    }

    @Override
    public void clearInput() {
        accountInput.getText().clear();
        pwdInput.getText().clear();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showResult(String resMsg) {
        ToastUtil.showToast(this, resMsg);
    }

    @Override
    public void jumpToHome() {
        jumpToActivity(MainActivity.class, true, null);
    }

    @Override
    public void jumpToRegist() {

    }
}
