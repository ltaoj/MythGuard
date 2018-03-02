package cn.ltaoj.mythguard.ui.fragment;

import android.view.View;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;

/**
 * Created by lenovo on 2018/3/2.
 */

public class VisitorFragment extends MVPBaseFragment {

    private int layoutId = R.layout.fragment_visitor;

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}
