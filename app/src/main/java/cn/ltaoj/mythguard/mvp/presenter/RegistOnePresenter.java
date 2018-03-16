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

    public void goBack() {
        mRegistView.prevFragment();
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
