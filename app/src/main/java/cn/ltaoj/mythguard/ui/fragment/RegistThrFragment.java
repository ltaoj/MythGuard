package cn.ltaoj.mythguard.ui.fragment;

import android.view.View;

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

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initView() {
        getRootView().findViewById(R.id.go_next).setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.go_back:
                mPresenter.goBack();
                break;
            case R.id.go_next:
                mPresenter.checkData();
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
}
