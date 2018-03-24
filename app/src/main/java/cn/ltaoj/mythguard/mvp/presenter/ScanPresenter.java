package cn.ltaoj.mythguard.mvp.presenter;

import android.graphics.SurfaceTexture;
import android.media.Image;
import android.util.Log;
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
     */
    private Camera2Helper.FrameProcessListener mFrameProcessListener = new Camera2Helper.FrameProcessListener() {
        @Override
        public void processImageFrame(Image frame) {
            List<AFD_FSDKFace> result = new ArrayList<>();
            int nRet = mFaceDetect.detect(frame, result);
            switch (nRet) {
                // do something
            }
        }
    };

    public ScanPresenter(IScanView scanView) {
        this.mScanView = scanView;
        this.mFaceDetect = new FaceDetect();
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
}
