package cn.ltaoj.mythguard.ui.fragment;

import android.view.View;

import java.lang.reflect.Member;
import java.util.List;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;
import cn.ltaoj.mythguard.mvp.presenter.MemberPresenter;
import cn.ltaoj.mythguard.mvp.view.IMemberView;

/**
 * Created by lenovo on 2018/3/2.
 */

public class MemberFragment extends MVPBaseFragment implements IMemberView {

    private int layoutId = R.layout.fragment_member;

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
        return new MemberPresenter(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showMembers(List<Member> members) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
