package cn.ltaoj.mythguard.mvp.presenter;

import cn.ltaoj.mythguard.mvp.model.RegistModel;
import cn.ltaoj.mythguard.mvp.model.impl.RegistModelimpl;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.network.RegistApi;
import cn.ltaoj.mythguard.network.impl.RegistApiimpl;

/**
 * Created by ltaoj on 2018/3/14 17:10.
 */

public class RegistPresenter extends BasePresenter<IRegistView> {

    /**
     * 主Activity，View角色
     */
    private IRegistView mRegistView;

    /**
     * 注册相关数据模型
     */
    private RegistModel mRegistModel = new RegistModelimpl();

    /**
     * 注册相关的网络请求api
     */
    private RegistApi mRegistApi = new RegistApiimpl();

    public RegistPresenter(IRegistView mRegistView) {
        this.mRegistView = mRegistView;
    }
}
