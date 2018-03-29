package cn.ltaoj.mythguard.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Arrays;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.mvp.presenter.ScanPresenter;
import cn.ltaoj.mythguard.mvp.view.IScanView;
import cn.ltaoj.mythguard.util.ToastUtil;
import cn.ltaoj.mythguard.widget.AutoFitTextureView;
import cn.ltaoj.widget.FacePreview;

/**
 * Created by ltaoj on 2018/3/20 16:13.
 */

public class ScanActivity extends MVPBaseActivity<IScanView, ScanPresenter> implements IScanView{
    private static final String TAG = "ScanActivity";

    private static final int layoutId = R.layout.activity_scan;

    private static final int PERMISSION_REQUEST_CODE = 98;

    private AutoFitTextureView mTextureView;
    private FacePreview mScanView;


//    private Camera2Helper mCamera2Helper;

//    private TextureView.SurfaceTextureListener mTextureListener = new TextureView.SurfaceTextureListener() {
//        @Override
//        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
//            mCamera2Helper.startPreview();
//        }
//
//        @Override
//        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//            mCamera2Helper.configureTransform(mTextureView.getWidth(), mTextureView.getHeight());
//        }
//
//        @Override
//        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//            return false;
//        }
//
//        @Override
//        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//
//        }
//    };

    @Override
    protected void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        mTextureView = findViewById(R.id.camera_view);
        mScanView = findViewById(R.id.scan_view);

        RelativeLayout.LayoutParams cameraViewParams = (RelativeLayout.LayoutParams) mTextureView.getLayoutParams();
        cameraViewParams.width = screenWidth;
        cameraViewParams.height = screenHeight;
        mTextureView.setLayoutParams(cameraViewParams);

        RelativeLayout.LayoutParams scanViewParams = (RelativeLayout.LayoutParams) mScanView.getLayoutParams();
        scanViewParams.width = screenWidth;
        scanViewParams.height = screenHeight;
        mScanView.setLayoutParams(scanViewParams);

        checkPermissions();
        configFacePreview();

        mPresenter.initCameraHelper();
//        mCamera2Helper = new Camera2Helper(this);
//        mCamera2Helper.setTextureView(mTextureView);
//        mCamera2Helper.setRect(mScanView.getRect());
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (mTextureView.isAvailable()) {
//            mCamera2Helper.startPreview();
//        } else {
//            mTextureView.setSurfaceTextureListener(mTextureListener);
//        }
        mPresenter.startPreview();
    }

    @Override
    protected void onPause() {
        mPresenter.stopPreview();
//        mCamera2Helper.stopPreview();
        super.onPause();
    }

    /**
     * 配置FacePreview
     */
    private void configFacePreview() {
        FacePreview.OnPreviewChangeListener changeListener = new FacePreview.OnPreviewChangeListener() {
            @Override
            public void onReady() {
//                ToastUtil.showToast(getApplicationContext(), "FacePreview ready");
            }

            @Override
            public void onPause() {
//                ToastUtil.showToast(getApplicationContext(), "FacePreview pause");
            }

            @Override
            public void onDetecting() {
//                ToastUtil.showToast(getApplicationContext(), "FacePreview detecting");
            }

            @Override
            public void onComplete() {
//                ToastUtil.showToast(getApplicationContext(), "FacePreview completed");
            }
        };
        FacePreview.PreviewConfig faceConfig = new FacePreview.PreviewConfig(250, 350,
                "将方框对准人脸，即可自动识别", true, changeListener);
        FacePreview.PreviewConfig codeConfig = new FacePreview.PreviewConfig(250, 250,
                "将二维码对准方框，即可自动识别", true, changeListener);
        mScanView.setPreviewConfigs(Arrays.asList(faceConfig, codeConfig));
    }

    // 请求相机权限
    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            int length = grantResults.length;
            boolean reRequest = false;
            for (int i = 0;i < length;i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "onRequestPermissionsResult: 权限申请成功");
                } else {
                    reRequest = true;
                    Log.i(TAG, "onRequestPermissionsResult: 权限申请失败");
                }
            }

            // 如果权限申请失败，那么直接退出此页面
            if (reRequest) {
                ToastUtil.showToast(this, "权限申请失败");
                this.finish();
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected ScanPresenter createPresenter() {
        return new ScanPresenter(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public AutoFitTextureView getTextureView() {
        return mTextureView;
    }

    @Override
    public RectF getPreviewRect() {
        return mScanView.getRect();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        mPresenter.pocsBackPressed();
    }
}
