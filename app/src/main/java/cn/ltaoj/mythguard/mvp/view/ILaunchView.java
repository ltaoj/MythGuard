package cn.ltaoj.mythguard.mvp.view;

/**
 * Created by ltaoj on 2018/3/31 20:20.
 */

public interface ILaunchView {

    /**
     * 显示登录、注册按钮
     */
    void showButtons();

    /**
     * 隐藏登录、注册按钮
     */
    void hideButtons();

    /**
     * 显示弹出式窗口
     */
    void showPopWnd();

    /**
     * 隐藏弹出式窗口
     */
    void hidePopWnd();

    /**
     * 跳转到注册页面
     */
    void jumpToRegistActivity();

    /**
     * 跳转到登录页面
     */
    void jumpToLoginActivity();

    /**
     * 跳转到主界面
     */
    void jumpToHomeActivity();
}
