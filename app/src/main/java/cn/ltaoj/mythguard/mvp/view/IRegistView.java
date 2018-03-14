package cn.ltaoj.mythguard.mvp.view;

/**
 * Created by ltaoj on 2018/3/14 17:09.
 */

public interface IRegistView {

    /**
     * 返回到上一个注册步骤
     */
    void prevFragment();

    /**
     * 下一个注册步骤
     */
    void nextFragment();

    /**
     * 注册完成后跳转到登录页面
     */
    void jumpToLogin();
}
