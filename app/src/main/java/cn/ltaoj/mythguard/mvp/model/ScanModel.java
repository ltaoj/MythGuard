package cn.ltaoj.mythguard.mvp.model;

import android.graphics.Bitmap;
import android.graphics.YuvImage;
import android.media.Image;

import com.arcsoft.facedetection.AFD_FSDKFace;

import java.io.File;

/**
 * Created by ltaoj on 2018/3/24 23:01.
 */

public interface ScanModel {

    /**
     * 保存脸部图像
     * @param yuvImage
     * @param face
     */
    void saveImage(YuvImage yuvImage, AFD_FSDKFace face);

    /**
     * 设置图片保存的路径
     * @param pFile
     */
    void setParentFile(File pFile);
}
