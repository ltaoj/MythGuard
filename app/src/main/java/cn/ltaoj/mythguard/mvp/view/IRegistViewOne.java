package cn.ltaoj.mythguard.mvp.view;

/**
 * Created by ltaoj on 2018/3/14 21:44.
 */

public interface IRegistViewOne {

    enum InputType {
        NAME,
        ID,
        PHONE,
    };

    /**
     * 获取界面输入框的值
     * @param inputType 指定输入框
     * @return 输入框中用户输入值
     */
    String getEditValue(InputType inputType);

    /**
     * 输入框显示错误等提示信息
     * @param inputType
     * @param tipMsg 提示信息
     */
    void showTip(InputType inputType, String tipMsg);

    /**
     * 输入框关闭提示
     */
    void hideTip();

    /**
     * 设置按钮的状态
     * @param enabled
     */
    void setEnabledBtn(boolean enabled);

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();
}
