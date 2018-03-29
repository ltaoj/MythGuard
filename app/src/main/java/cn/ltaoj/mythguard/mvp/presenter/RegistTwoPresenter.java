package cn.ltaoj.mythguard.mvp.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.mvp.model.ScanModel;
import cn.ltaoj.mythguard.mvp.model.impl.ScanModelimpl;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewTwo;
import cn.ltaoj.mythguard.util.ToastUtil;

/**
 * Created by ltaoj on 2018/3/14 22:15.
 */

public class RegistTwoPresenter extends BasePresenter<IRegistViewTwo> {
    private static final String TAG = "RegistTwoPresenter";

    // activity for result request code
    public static final int ACTIVITY_REQUEST_CODE = 1;
    // activity for scan activity result code
    public static final int ACTIVITY_RESULT_CODE = 5;

    // scan success
    public static final int SCAN_RESULT_OK = 1;
    // scan failed or scan cancel
    public static final int SCAN_RESULT_FAILED = 2;

    private IRegistView mRegistView;

    private IRegistViewTwo mRegistViewTwo;

    // 扫描界面模型，用于读取读片文件
    private ScanModel mScanModel = new ScanModelimpl();

    public RegistTwoPresenter(IRegistViewTwo mRegistViewTwo, IRegistView mRegistView) {
        this.mRegistView = mRegistView;
        this.mRegistViewTwo = mRegistViewTwo;
        this.mScanModel.setParentFile(mRegistViewTwo.getViewContext().getExternalFilesDir(null));
    }

    public boolean hasRegistView() {
        return mRegistView != null;
    }

    public void setmRegistView(IRegistView mRegistView) {
        this.mRegistView = mRegistView;
    }

    public void goBack() {
        mRegistView.prevFragment();
    }

    public void checkData() {
        /**
         * 页面数据验证，存储后，如果成功，进入下一个页面，失败，则给出相应提示
         */
        if (mRegistView != null) {
            mRegistView.nextFragment();
        }
    }

    public void listenScanResult(int requestCode, int resultCode, Intent data) {
        // 从ScanActivity返回的结果码
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == ACTIVITY_RESULT_CODE) {
            int scanResult = data.getIntExtra("scanRes", SCAN_RESULT_FAILED);
            if (scanResult == SCAN_RESULT_OK) {
                Bitmap bitmap = mScanModel.loadImage(ScanModel.ImageType.FACE);
                if (bitmap != null) {
                    mRegistViewTwo.setPreviewImage(bitmap);
                }
                mRegistViewTwo.setEnabledBtn(true);
                mRegistViewTwo.changeBtnText(R.string.ar_detect_again);
                ToastUtil.showToast(mRegistViewTwo.getViewContext(), "识别成功，请点击下一步继续注册");
            } else {
                // 先从本地加载图片
                Bitmap bitmap = mScanModel.loadImage(ScanModel.ImageType.FACE);
                Log.d(TAG, "listenScanResult: ");
                if (bitmap != null) {
                    mRegistViewTwo.setPreviewImage(bitmap);
                    mRegistViewTwo.setEnabledBtn(true);
                    mRegistViewTwo.changeBtnText(R.string.ar_detect_again);
                } else {
                    mRegistViewTwo.setEnabledBtn(false);
                    mRegistViewTwo.changeBtnText(R.string.ar_start_detect);
                }
            }
        }
    }
}
