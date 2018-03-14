package cn.ltaoj.mythguard.mvp.presenter;

import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewOne;

/**
 * Created by ltaoj on 2018/3/14 22:15.
 */

public class RegistOnePresenter extends BasePresenter<IRegistViewOne> {

    private IRegistView mRegistView;

    private IRegistViewOne mRegistViewOne;


    public RegistOnePresenter(IRegistViewOne mRegistViewOne, IRegistView mRegistView) {
        this.mRegistView = mRegistView;
        this.mRegistViewOne = mRegistViewOne;
    }

    public boolean hasRegistView() {
        return mRegistView != null;
    }

    public void setmRegistView(IRegistView mRegistView) {
        this.mRegistView = mRegistView;
    }
}
