package cn.ltaoj.mythguard.media;

import android.graphics.Rect;
import android.media.Image;
import android.util.Log;

import com.arcsoft.facedetection.AFD_FSDKEngine;
import com.arcsoft.facedetection.AFD_FSDKError;
import com.arcsoft.facedetection.AFD_FSDKFace;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ltaoj on 2018/3/24 21:26.
 * 该类对虹软SDK功能进行封装调用
 */

public class FaceDetect implements IFaceDetect<AFD_FSDKFace>{
    private static final String TAG = "FaceDetect";

    // SDK的APPID
    private static final String FD_APP_ID = "BeQUado7GgZXX7B6tNvRicYd8SAxxHYK9X1srSYhkk3z";
    // SDK的SDK KEY
    private static final String FD_SDK_KEY = "FqDgzcMmZbeJfsxCfp9C9JWcuufWYD5aQMBfKwTFrSBp";
    private static final boolean DEBUG = true;

    // 没有检测到人脸
    public static final int FACE_NO_RESULT = -4;
    // 脸部角度不符合要求
    public static final int FACE_DEGREE_NO_MATCH = -3;
    // 脸部大小不符合要求
    public static final int FACE_AREA_NO_MATCH = -2;
    // 参数错误或者调用错误引起的异常
    public static final int NORMAL_ERROR = -1;

    private AFD_FSDKEngine mEngine;

    /**
     * 默认构造函数
     */
    public FaceDetect() {
        mEngine = null;
    }

    /**
     * 初始化引擎
     * @return 如果初始化失败，返回错误
     */
    public AFD_FSDKError initEngine() {
        AFD_FSDKError err = null;
        try {
            mEngine = new AFD_FSDKEngine();
            // 参数为角度、范围、最多检测人脸数目
            err = mEngine.AFD_FSDK_InitialFaceEngine(FD_APP_ID, FD_SDK_KEY, AFD_FSDKEngine.AFD_OPF_0_HIGHER_EXT, 16, 5);
            if (DEBUG) {
                Log.d(TAG, "initEngine: error = " + err.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return err;
    }

    public boolean needInitEngine() {
        return mEngine == null;
    }

    /**
     * 释放引擎
     */
    public void closeEngine() {
        try {
            mEngine.AFD_FSDK_UninitialFaceEngine();
            mEngine = null;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param image 可能包含人脸的图像
     * @param result 保存识别结果
     * @return 推荐结果下标, 没有推荐的返回-1
     */
    @Override
    public int detect(Image image, List<AFD_FSDKFace> result) {
        if (image == null) return NORMAL_ERROR;

        int width = image.getWidth();
        int height = image.getHeight();
        if (height % 2 != 0) height++;

        AFD_FSDKError err = process(convertBytes(image), width, height, result);

        if (DEBUG) {
            Log.d(TAG, "detect: error = " + err.getCode());
        }

        AFD_FSDKFace face = null;

        // 首先获得图像中面积最大的人脸
        if (result.size() > 1) {
            face = Collections.max(result, new ComparaFaceByArea());
        } else if (result.size() > 0) {
            face = result.get(0);
        }

        if (face == null) {
            return FACE_NO_RESULT;
        }

        if (isFaceTooSmall(image, face.getRect())) {
            return FACE_AREA_NO_MATCH;
        }
        if (DEBUG) {
            Log.d(TAG, "detect: degree = " + face.getDegree());
        }
        if (!isFacePortrait(face.getDegree())) {
            return FACE_DEGREE_NO_MATCH;
        }

        return result.indexOf(face);
    }

    /**
     * 将Image对象转换为byte数据
     * @param image
     * @return
     */
    private byte[] convertBytes(Image image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

    /**
     * 检测实际处理函数
     * @param data 图像byte数据
     * @param width 图像像素宽度
     * @param height 图像像素高度,height不能为奇数
     */
    private AFD_FSDKError process(byte[] data, int width, int height, List<AFD_FSDKFace> result) {
        AFD_FSDKError err = null;

        try {
            err = mEngine.AFD_FSDK_StillImageFaceDetection(data, width, height, AFD_FSDKEngine.CP_PAF_NV21, result);
            if (DEBUG) {
                Log.d(TAG, "process: error = " + err.getCode());
                Log.d(TAG, "process: faces size = " + result.size());
                for (AFD_FSDKFace face : result) {
                    Log.d(TAG, "process: face = " + face.toString());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return err;
    }

    private class ComparaFaceByArea implements Comparator<AFD_FSDKFace> {

        @Override
        public int compare(AFD_FSDKFace o1, AFD_FSDKFace o2) {
            return Long.signum(o1.getRect().width() * o1.getRect().height() - o2.getRect().width() * o2.getRect().height());
        }
    }

    /**
     * 判断图片中脸部是否太小
     * @param image
     * @param rect
     * @return
     */
    private boolean isFaceTooSmall(Image image, Rect rect) {
        int faceArea = rect.width() * rect.height();
        return faceArea <= (image.getWidth() * image.getHeight()) * 0.4;
    }

    private boolean isFacePortrait(int degree) {
        return degree <= 15 || degree >= 345;
    }
}
