package cn.ltaoj.mythguard.media;

import android.graphics.Bitmap;
import android.graphics.YuvImage;
import android.media.Image;

import java.util.List;

/**
 * Created by ltaoj on 2018/3/24 21:27.
 * 人脸检测接口类
 */

public interface IFaceDetect<T> {

    /**
     * 对Image图片检测人脸
     * @param yuvImage 可能包含人脸的图像
     * @param result 保存识别结果
     * @return 推荐结果下标, 没有推荐的返回-1
     */
    int detect(YuvImage yuvImage, List<T> result);
}
