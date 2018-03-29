package cn.ltaoj.mythguard.mvp.presenter;

import android.view.View;

import java.io.File;

import cn.ltaoj.mythguard.bean.CtmResult;
import cn.ltaoj.mythguard.bean.RegistData;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.RegistModel;
import cn.ltaoj.mythguard.mvp.model.ScanModel;
import cn.ltaoj.mythguard.mvp.model.impl.RegistModelimpl;
import cn.ltaoj.mythguard.mvp.model.impl.ScanModelimpl;
import cn.ltaoj.mythguard.mvp.view.IRegistView;
import cn.ltaoj.mythguard.mvp.view.IRegistViewThr;
import cn.ltaoj.mythguard.network.RegistApi;
import cn.ltaoj.mythguard.network.impl.RegistApiimpl;
import cn.ltaoj.mythguard.util.ToastUtil;

/**
 * Created by ltaoj on 2018/3/14 22:16.
 */

public class RegistThrPresenter extends BasePresenter<IRegistViewThr> {

    private IRegistView mRegistView;

    private IRegistViewThr mRegistViewThr;

    private RegistModel mRegistModel = new RegistModelimpl();

    private ScanModel mScanModel = new ScanModelimpl();

    private RegistApi mRegistApi = new RegistApiimpl();

    public RegistThrPresenter(IRegistViewThr mRegistViewThr, IRegistView mRegistView) {
        this.mRegistView = mRegistView;
        this.mRegistViewThr = mRegistViewThr;
        mScanModel.setParentFile(mRegistView.getActivity().getExternalFilesDir(null));
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

    /**
     * 初始化控件可见性、按钮可点击状态
     */
    public void initView() {
        String type = mRegistModel.getRegistType();
        if (type != null && type.equals("")) {
            type = "户主";
        }

        if (type.equals("户主")) {
            // 对于户主，需要输入门牌号
            mRegistViewThr.setInputVisibility(IRegistViewThr.InputType.HOUSE_NUMBER, View.VISIBLE);
            mRegistViewThr.setInputVisibility(IRegistViewThr.InputType.HOLDER_ID, View.GONE);
        } else if (type.equals("常驻住户")) {
            mRegistViewThr.setInputVisibility(IRegistViewThr.InputType.HOLDER_ID, View.VISIBLE);
            mRegistViewThr.setInputVisibility(IRegistViewThr.InputType.HOUSE_NUMBER, View.GONE);
        }

        mRegistViewThr.enableBtn(false);
    }

    /**
     * 监听输入框
     */
    public void listenInput(int which) {
        String inputValue = null;
        // 0表示户主注册
        if (which == 0) {
            inputValue = mRegistViewThr.getEditValue(IRegistViewThr.InputType.HOUSE_NUMBER);
            mRegistApi.queryAvailByHouseNum(inputValue, new DataListener<CtmResult>() {
                @Override
                public void onComplete(CtmResult result) {
                    if (result != null) {
                        mRegistViewThr.enableBtn(result.isSuccess());
                    }
                }
            });
        } else if (which == 1){
            inputValue = mRegistViewThr.getEditValue(IRegistViewThr.InputType.HOLDER_ID);
            mRegistApi.queryAvailByHolderId(inputValue, new DataListener<CtmResult>() {
                @Override
                public void onComplete(CtmResult result) {
                    if (result != null) {
                        mRegistViewThr.enableBtn(result.isSuccess());
                    }
                }
            });
        }
    }

    /**
     * 注册，从本地获取缓存信息，通过api提交后台
     */
    public void doRegist() {
        mRegistViewThr.showLoading();
        // 从本地获取每一步填写的数据
        mRegistModel.loadRegistData(new DataListener<RegistData>() {
            @Override
            public void onComplete(final RegistData result) {
                if (result == null) {
                    mRegistViewThr.hideLoading();
                    return;
                }

                File zipImages = mScanModel.loadZipImage(ScanModel.ImageType.FACE);
                if (zipImages == null) {
                    mRegistViewThr.hideLoading();
                    return;
                }

                // 首先将图片上传，然后服务器返回图片再服务器的地址
                // 根绝返回的地址，将result对象的facePath字段填充，发起注册请求
                // 1. uploadImage
                mRegistApi.uploadImages(zipImages, new DataListener<CtmResult>() {
                    @Override
                    public void onComplete(CtmResult upResult) {
                        mRegistViewThr.hideLoading();

                        if (upResult.isSuccess()) {
                            String facePath = upResult.getUrl();
                            result.setFacePath(facePath);
                            mRegistViewThr.showLoading();

                            // 2. regist
                            mRegistApi.regist(result, new DataListener<CtmResult>() {
                                @Override
                                public void onComplete(CtmResult result) {
                                    mRegistViewThr.hideLoading();
                                    if (result.isSuccess()) {
                                        clearModel();
                                        mRegistView.nextFragment();
                                        ToastUtil.showToast(mRegistView.getActivity(), "注册成功");
                                    } else {
                                        ToastUtil.showToast(mRegistView.getActivity(), "注册失败");
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void clearModel() {
        mRegistModel.clear();
        mScanModel.deleteAllImages();
    }
}
