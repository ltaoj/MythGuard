package cn.ltaoj.mythguard.mvp.model;

import cn.ltaoj.mythguard.bean.Signon;
import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by ltaoj on 2018/3/13 14:57.
 */

public interface SignonModel {

    /**
     * 登录成功后，本地记录登录信息，方便自动登录
     */
    void saveSignonInfo(Signon signon);

    /**
     * 自动登录时，从本地存储获取登录信息
     * @param listener
     */
    void loadSiFromLocalDB(DataListener<Signon> listener);

    /**
     * 清除本地登录信息
     */
    void clearSignonInfo();
}
