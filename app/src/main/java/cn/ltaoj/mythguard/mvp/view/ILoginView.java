package cn.ltaoj.mythguard.mvp.view;

/**
 * Created by ltaoj on 2018/3/13 14:46.
 */

public interface ILoginView {

    enum InputType { ACCOUNT, PWD };

    /**
     * 获取账号
     * @return
     */
    String getAccount();

    /**
     * 获取密码
     * @return
     */
    String getPwd();

    /**
     * 输入框显示提示
     * @param inputType
     */
    void showTip(InputType inputType);

    /**
     * 输入框关闭提示
     */
    void hideTip();

    /**
     * 清除输入框内容
     */
    void clearInput();

    /**
     * 显示加载信息
     * @param
     */
    void showLoading();

    /**
     * 关闭加载信息
     */
    void hideLoading();

    /**
     * 显示登录结果
     */
    void showResult(String resMsg);

    /**
     * 跳转到主界面
     */
    void jumpToHome();

    /**
     * 跳转到注册界面
     */
    void jumpToRegist();
}
