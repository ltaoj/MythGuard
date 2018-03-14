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

    public void checkData() {
        /**
         * 页面数据验证，存储后，如果成功，进入下一个页面，失败，则给出相应提示
         */
        if (mRegistView != null) {
            mRegistView.nextFragment();
        }
    }
}
