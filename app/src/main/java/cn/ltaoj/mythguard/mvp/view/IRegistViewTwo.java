package cn.ltaoj.mythguard.mvp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by ltaoj on 2018/3/14 21:44.
 */

public interface IRegistViewTwo {

    /**
     * 获取View的上下文
     * @return
     */
    Context getViewContext();

    /**
     * 设置按钮的状态
     * @param enabled
     */
    void setEnabledBtn(boolean enabled);

    /**
     * 设置检测按钮上的文本
     * @param id
     */
    void changeBtnText(int id);

    /**
     * 设置ImageVIew预览图片
     * @param bitmap
     */
    void setPreviewImage(Bitmap bitmap);
}
