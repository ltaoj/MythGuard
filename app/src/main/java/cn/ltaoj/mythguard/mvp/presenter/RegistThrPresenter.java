package cn.ltaoj.mythguard.mvp.presenter;

import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewThr;

/**
 * Created by ltaoj on 2018/3/14 22:16.
 */

public class RegistThrPresenter extends BasePresenter<IRegistViewThr> {

    private IRegistView mRegistView;

    private IRegistViewThr mRegistViewThr;

    public RegistThrPresenter(IRegistViewThr mRegistViewThr, IRegistView mRegistView) {
        this.mRegistView = mRegistView;
        this.mRegistViewThr = mRegistViewThr;
    }

    public boolean hasRegistView() {
        return mRegistView != null;
    }

    public void setmRegistView(IRegistView mRegistView) {
        this.mRegistView = mRegistView;
    }
}
