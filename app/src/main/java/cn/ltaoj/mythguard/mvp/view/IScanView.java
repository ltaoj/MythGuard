package cn.ltaoj.mythguard.mvp.view;

import android.app.Activity;
import android.graphics.RectF;

import cn.ltaoj.mythguard.widget.AutoFitTextureView;

/**
 * Created by ltaoj on 2018/3/24 23:00.
 */

public interface IScanView {

    /**
     * @return 返回View的上下文
     */
    Activity getActivity();

    /**
     * 返回TextureView
     * @return
     */
    AutoFitTextureView getTextureView();

    /**
     * 获取扫描矩形框Rect
     * @return
     */
    RectF getPreviewRect();

    /**
     * 关闭当前Activity
     */
    void finishActivity();
}
