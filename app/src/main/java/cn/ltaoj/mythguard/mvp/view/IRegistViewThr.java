package cn.ltaoj.mythguard.mvp.view;

import android.app.Activity;

/**
 * Created by ltaoj on 2018/3/14 21:45.
 */

public interface IRegistViewThr {

    enum InputType {
        HOUSE_NUMBER,
        HOLDER_ID
    }

    /**
     * 隐藏疑问白板
     */
    void hideDoubtPanel();

    /**
     * 在Doubt-ImageView左下方显示疑问白板
     */
    void showDoubtPannel();

    /**
     * 设置View的可见性
     * @param inputType
     * @param visibility 可取值为
     * {@link android.view.View#VISIBLE}
     * {@link android.view.View#INVISIBLE}
     * {@link android.view.View#GONE}
     */
    void setInputVisibility(InputType inputType, int visibility);

    /**
     * 设置按钮可点击状态
     * @param enabled
     */
    void enableBtn(boolean enabled);

    /**
     * 获取输入框的值
     * @param inputType 指明哪个输入框
     * @return
     */
    String getEditValue(InputType inputType);

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 关闭加载动画
     */
    void hideLoading();
}
