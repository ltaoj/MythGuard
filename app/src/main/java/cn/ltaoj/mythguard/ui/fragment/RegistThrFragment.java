package cn.ltaoj.mythguard.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.mvp.presenter.RegistThrPresenter;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewThr;

/**
 * Created by ltaoj on 2018/3/14 22:15.
 */

public class RegistThrFragment extends MVPBaseFragment<IRegistViewThr, RegistThrPresenter> implements IRegistViewThr {
    private static final String TAG = "RegistThrFragment";

    private final int layoutId = R.layout.fragment_regist_thr;

    private IRegistView registView;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.listenInput(mHouseNumInput.getVisibility() == View.VISIBLE ? 0 : 1);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // 门牌号输入框
    private EditText mHouseNumInput;
    // 户主编号输入框
    private EditText mHolderIdInput;

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initView() {
        getRootView().findViewById(R.id.go_next).setOnClickListener(this);
        getRootView().findViewById(R.id.go_back).setOnClickListener(this);
        getRootView().findViewById(R.id.iv_dooubt).setOnClickListener(this);

        mHouseNumInput = getRootView().findViewById(R.id.input_house_number);
        mHolderIdInput = getRootView().findViewById(R.id.input_holder_id);

        mHouseNumInput.addTextChangedListener(mTextWatcher);
        mHolderIdInput.addTextChangedListener(mTextWatcher);

        // 初始化控件的可见性，以及按钮可点击状态
        mPresenter.initView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegistThrPresenter createPresenter() {
        return new RegistThrPresenter(this, registView);
    }

    @Override
    public void onClick(View v) {
        hideDoubtPanel();

        switch (v.getId()) {
            case R.id.go_back:
                mPresenter.goBack();
                break;
            case R.id.go_next:
                mPresenter.doRegist();
                break;
            case R.id.iv_dooubt:
                // 显示疑问白板
                showDoubtPannel();
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
    public void hideDoubtPanel() {

    }

    @Override
    public void showDoubtPannel() {

    }

    @Override
    public void setInputVisibility(InputType inputType, int visibility) {
        if ((inputType != InputType.HOUSE_NUMBER && inputType != InputType.HOLDER_ID) ||
                (visibility != View.VISIBLE && visibility != View.INVISIBLE && visibility != View.GONE)) {
            throw new IllegalArgumentException("inputType must be HOUSE_NUMBER or HOLDER_ID, " +
                    "visibility must be VISIBLE, INVISIBLE or GONE");
        }

        if (inputType == InputType.HOUSE_NUMBER) {
            mHouseNumInput.setVisibility(visibility);
        } else if (inputType == InputType.HOLDER_ID) {
            mHolderIdInput.setVisibility(visibility);
        }
    }

    @Override
    public void enableBtn(boolean enabled) {
        getRootView().findViewById(R.id.go_next).setEnabled(enabled);
    }

    @Override
    public String getEditValue(InputType inputType) {
        String value = "";
        if (inputType == InputType.HOUSE_NUMBER) {
            value = mHouseNumInput.getText().toString();
        } else if (inputType == InputType.HOLDER_ID) {
            value = mHolderIdInput.getText().toString();
        }
        return value;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
