package cn.ltaoj.mythguard.mvp.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.media.Image;
import android.view.TextureView;

import com.arcsoft.facedetection.AFD_FSDKFace;

import java.util.ArrayList;
import java.util.List;

import cn.ltaoj.mythguard.media.Camera2Helper;
import cn.ltaoj.mythguard.media.FaceDetect;
import cn.ltaoj.mythguard.media.IFaceDetect;
import cn.ltaoj.mythguard.mvp.model.ScanModel;
import cn.ltaoj.mythguard.mvp.model.impl.ScanModelimpl;
import cn.ltaoj.mythguard.mvp.view.IScanView;
import cn.ltaoj.mythguard.util.ToastUtil;

import static cn.ltaoj.mythguard.mvp.presenter.RegistTwoPresenter.ACTIVITY_RESULT_CODE;
import static cn.ltaoj.mythguard.mvp.presenter.RegistTwoPresenter.SCAN_RESULT_FAILED;
import static cn.ltaoj.mythguard.mvp.presenter.RegistTwoPresenter.SCAN_RESULT_OK;

/**
 * Created by ltaoj on 2018/3/24 23:00.
 */

public class ScanPresenter extends BasePresenter<IScanView> {
    private static final String TAG = "ScanPresenter";

    // 相机管理帮助类
    private Camera2Helper mCamera2Helper;

    // ScanActivity的View角色
    private IScanView mScanView;

    // Scan界面相关Model类，负责本地存储
    private ScanModel mScanModel = new ScanModelimpl();

    private TextureView.SurfaceTextureListener mTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            mCamera2Helper.startPreview();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            mCamera2Helper.configureTransform(mScanView.getTextureView().getWidth(), mScanView.getTextureView().getHeight());
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private IFaceDetect mFaceDetect = null;

    /**
     * 图像帧处理接口
     * 待优化的地方，这个回调函数基本上一直在进行，所以会一直弹出Toast，应进行控制
     * 可以设置定时器，多长时间返回相同状态，弹出Toast
     */
    private Camera2Helper.FrameProcessListener mFrameProcessListener = new Camera2Helper.FrameProcessListener() {
        @Override
        public void processImageFrame(YuvImage frame) {
            List<AFD_FSDKFace> result = new ArrayList<>();
            int nRet = mFaceDetect.detect(frame, result);
            switch (nRet) {
                case FaceDetect.FACE_NO_RESULT:
                    ToastUtil.showToast(mScanView.getActivity(), "请将脸部对准矩形区域");
                    break;
                case FaceDetect.NORMAL_ERROR:
                    ToastUtil.showToast(mScanView.getActivity(), "检测内部错误");
                    break;
                case FaceDetect.FACE_AREA_NO_MATCH:
                    ToastUtil.showToast(mScanView.getActivity(), "请尽量将脸部占满矩形区域");
                    break;
                case FaceDetect.FACE_DEGREE_NO_MATCH:
                    ToastUtil.showToast(mScanView.getActivity(), "请尽量将脸部和屏幕保持竖直");
                    break;
                default:
                    // 符合要求的结果
                    AFD_FSDKFace face = result.get(nRet); // 包含frame中脸部的位置以及角度
                    // 将frame保存到本地
                    mScanModel.saveImage(frame, face);
                    // 返回值
                    Intent intent = new Intent();
                    intent.putExtra("scanRes", SCAN_RESULT_OK);
                    mScanView.getActivity().setResult(ACTIVITY_RESULT_CODE, intent);
                    // 关闭当前活动页面
                    mScanView.finishActivity();
                    break;
            }
        }
    };

    public ScanPresenter(IScanView scanView) {
        this.mScanView = scanView;
        this.mFaceDetect = new FaceDetect();
        this.mScanModel.setParentFile(mScanView.getActivity().getExternalFilesDir(null));
    }

    /**
     * 初始化相机帮助类
     */
    public void initCameraHelper() {
        mCamera2Helper = new Camera2Helper(mScanView.getActivity());
        mCamera2Helper.setTextureView(mScanView.getTextureView());
        mCamera2Helper.setRect(mScanView.getPreviewRect());
        mCamera2Helper.setFrameProcessListener(mFrameProcessListener);
    }

    /**
     * 开始预览
     */
    public void startPreview() {
        FaceDetect faceDetect = (FaceDetect) mFaceDetect;
        if (faceDetect.needInitEngine()) {
            faceDetect.initEngine();
        }

        if (mScanView.getTextureView().isAvailable()) {
            mCamera2Helper.startPreview();
        } else {
            mScanView.getTextureView().setSurfaceTextureListener(mTextureListener);
        }
    }

    /**
     * 停止预览
     */
    public void stopPreview() {
        mCamera2Helper.stopPreview();

        // 回收识别引擎，释放内存
        FaceDetect faceDetect = (FaceDetect) mFaceDetect;
        if (!faceDetect.needInitEngine()) {
            faceDetect.closeEngine();
        }
    }

    /**
     * 处理返回按钮事件
     */
    public void pocsBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("scanRes", SCAN_RESULT_FAILED);
        mScanView.getActivity().setResult(ACTIVITY_RESULT_CODE, intent);
        mScanView.finishActivity();
    }
}
