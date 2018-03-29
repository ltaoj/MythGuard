package cn.ltaoj.mythguard.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.mvp.presenter.RegistTwoPresenter;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewTwo;
import cn.ltaoj.mythguard.ui.activity.ScanActivity;

import static cn.ltaoj.mythguard.mvp.presenter.RegistTwoPresenter.ACTIVITY_REQUEST_CODE;

/**
 * Created by ltaoj on 2018/3/14 22:14.
 */

public class RegistTwoFragment extends MVPBaseFragment<IRegistViewTwo, RegistTwoPresenter> implements IRegistViewTwo {
    private static final String TAG = "RegistTwoFragment";

    private final int layoutId = R.layout.fragment_regist_two;

    private IRegistView registView;

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initView() {
        Button button = getRootView().findViewById(R.id.go_next);
        button.setOnClickListener(this);
        button.setEnabled(false);
        getRootView().findViewById(R.id.go_back).setOnClickListener(this);

        getRootView().findViewById(R.id.face_auth).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegistTwoPresenter createPresenter() {
        return new RegistTwoPresenter(this, registView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                mPresenter.goBack();
                break;
            case R.id.go_next:
                mPresenter.checkData();
                break;
            case R.id.face_auth:
//                jumpToActivity(ScanActivity.class, null);
                startActivityForResult(new Intent(getContext(), ScanActivity.class), ACTIVITY_REQUEST_CODE);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.listenScanResult(requestCode, resultCode, data);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void setEnabledBtn(boolean enabled) {
        getRootView().findViewById(R.id.go_next).setEnabled(enabled);
    }

    @Override
    public void changeBtnText(int id) {
        Button detectBtn = getRootView().findViewById(R.id.face_auth);

        String btnText = getResources().getString(id);
        if (btnText != null && !btnText.equals("")) {
            detectBtn.setText(btnText);
        }
    }

    @Override
    public void setPreviewImage(Bitmap bitmap) {
        ImageView imageView = getRootView().findViewById(R.id.preview_image);
        imageView.setImageBitmap(bitmap);
    }
}
