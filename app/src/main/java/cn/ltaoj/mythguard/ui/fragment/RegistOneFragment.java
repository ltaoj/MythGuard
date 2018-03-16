package cn.ltaoj.mythguard.ui.fragment;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.mvp.presenter.RegistOnePresenter;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewOne;
import cn.ltaoj.mythguard.util.ToastUtil;

/**
 * Created by ltaoj on 2018/3/14 22:14.
 */

public class RegistOneFragment extends MVPBaseFragment<IRegistViewOne, RegistOnePresenter> implements IRegistViewOne {
    private static final String TAG = "RegistOneFragment";

    private final int layoutId = R.layout.fragment_regist_one;

    private IRegistView registView;

    private EditText inputName;
    private EditText inputID;
    private EditText inputPhone;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.listenInput();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initView() {
        Button nextBtn = getRootView().findViewById(R.id.go_next);
        nextBtn.setOnClickListener(this);
        // 设置按钮的初始状态
        nextBtn.setEnabled(false);
        getRootView().findViewById(R.id.go_back).setOnClickListener(this);
        // 服务条款和隐私协议
        getRootView().findViewById(R.id.terms_of_use).setOnClickListener(this);
        getRootView().findViewById(R.id.privacy_policy).setOnClickListener(this);

        inputName = getRootView().findViewById(R.id.input_name);
        inputID = getRootView().findViewById(R.id.input_ID);
        inputPhone = getRootView().findViewById(R.id.input_phone);

        // 监视文本框
        inputName.addTextChangedListener(mTextWatcher);
        inputID.addTextChangedListener(mTextWatcher);
        inputPhone.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegistOnePresenter createPresenter() {
        return new RegistOnePresenter(this, registView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                mPresenter.goBack();
                break;
            case R.id.go_next:
                mPresenter.tryToNext();
                break;
        }
    }

    /**
     * 改注入函数如果在对象创建之后，视图onCreateView函数之前进行调用
     * 直接将registView赋值给成员变量
     * 如果在视图onCreateView函数之后进行调用，那么会判断Presenter是否为null
     * 如果Presenter不为null，但是Presenter的registView为null，则直接注入到Presenter
     * @param registView
     */
    public void setRegistView(IRegistView registView) {
        if (mPresenter != null && !mPresenter.hasRegistView()) {
            mPresenter.setmRegistView(registView);
        } else {
            this.registView = registView;
        }
    }

    @Override
    public String getEditValue(InputType inputType) {
        String input = "";
        if (inputType == InputType.NAME) {
            input = inputName.getText().toString();
        } else if (inputType == InputType.ID) {
            input = inputID.getText().toString();
        } else if (inputType == InputType.PHONE) {
            input = inputPhone.getText().toString();
        }
        return input;
    }

    @Override
    public void showTip(InputType inputType, String tipMsg) {
        EditText editText = getEditText(inputType);
        if (editText != null) {
            editText.requestFocus();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                editText.setError(tipMsg);
            } else {
                ToastUtil.showToast(getContext(), tipMsg);
            }
        }
    }

    private EditText getEditText(InputType inputType) {
        EditText editText = null;
        if (inputType == InputType.NAME) {
            editText = inputName;
        } else if (inputType == InputType.ID) {
            editText = inputID;
        } else if (inputType == InputType.PHONE) {
            editText = inputPhone;
        }
        return editText;
    }

    @Override
    public void hideTip() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            inputName.setError(null);
            inputID.setError(null);
            inputPhone.setError(null);
        }
    }

    @Override
    public void setEnabledBtn(boolean enabled) {
        getRootView().findViewById(R.id.go_next).setEnabled(enabled);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
