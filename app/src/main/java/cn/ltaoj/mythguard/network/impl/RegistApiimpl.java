package cn.ltaoj.mythguard.network.impl;

import java.io.File;

import cn.ltaoj.mythguard.bean.CtmResult;
import cn.ltaoj.mythguard.bean.RegistData;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.network.RegistApi;

/**
 * Created by ltaoj on 2018/3/14 21:47.
 */

public class RegistApiimpl implements RegistApi {
    @Override
    public void queryAvailByHouseNum(String houseNum, DataListener<CtmResult> listener) {
        CtmResult result = new CtmResult(true, 0, "未绑定", "");
        listener.onComplete(result);
    }

    @Override
    public void queryAvailByHolderId(String holderId, DataListener<CtmResult> listener) {

    }

    @Override
    public void uploadImages(File zipImages, DataListener<CtmResult> listener) {
        CtmResult result = new CtmResult(true, 0, "上传成功",
                "https://www.ltaoj.cn/images/unresolved/H1234567-2018-03-29.zip");
        listener.onComplete(result);
    }

    @Override
    public void regist(RegistData registData, DataListener<CtmResult> listener) {
        CtmResult result = new CtmResult(true, 0, "注册成功", "");
        listener.onComplete(result);
    }
}
