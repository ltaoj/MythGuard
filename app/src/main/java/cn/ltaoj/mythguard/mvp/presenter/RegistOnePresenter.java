package cn.ltaoj.mythguard.mvp.presenter;

import cn.ltaoj.mythguard.mvp.model.RegistModel;
import cn.ltaoj.mythguard.mvp.model.impl.RegistModelimpl;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewOne;
import cn.ltaoj.mythguard.network.RegistApi;
import cn.ltaoj.mythguard.network.impl.RegistApiimpl;

/**
 * Created by ltaoj on 2018/3/14 22:15.
 */

public class RegistOnePresenter extends BasePresenter<IRegistViewOne> {

    private IRegistView mRegistView;

    private IRegistViewOne mRegistViewOne;

    private RegistModel mRegistModel = new RegistModelimpl();


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

    public void listenInput() {
        boolean enable = true;

        String name = mRegistViewOne.getEditValue(IRegistViewOne.InputType.NAME);
        String ID = mRegistViewOne.getEditValue(IRegistViewOne.InputType.ID);
        String phone = mRegistViewOne.getEditValue(IRegistViewOne.InputType.PHONE);

        /* 1&0=0, 0&0=0, 1&1=1*/
        enable &= validName(name);
        enable &= validID(ID);
        enable &= validPhone(phone);

        mRegistViewOne.setEnabledBtn(enable);
    }

    private boolean validName(String name) {
        if (name == null || name.trim().equals("")) return false;
        return true;
    }

    private boolean validID(String ID) {
        if (ID == null || ID.trim().equals("") || ID.trim().length() != 18) return false;
        // 根据身份证规则继续判断，不符合，返回false
        return true;
    }

    private boolean validPhone(String phone) {
        if (phone == null || phone.trim().equals("") || phone.trim().length() != 11) return false;
        try {
            Long.valueOf(phone);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 跳转到下一个页面的逻辑方法
     * 对数据进行简单存储或者执行网络请求，将跳转到下一个注册页面
     * 否则，给予提示
     */
    public void tryToNext() {
        String name = mRegistViewOne.getEditValue(IRegistViewOne.InputType.NAME);
        String ID = mRegistViewOne.getEditValue(IRegistViewOne.InputType.ID);
        String phone = mRegistViewOne.getEditValue(IRegistViewOne.InputType.PHONE);

        // 存储部分注册数据
        mRegistModel.saveStepOne(name, ID, phone);
        if (mRegistView != null) {
            mRegistView.nextFragment();
        }
    }
}
