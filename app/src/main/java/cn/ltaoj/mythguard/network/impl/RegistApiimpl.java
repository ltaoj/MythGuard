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

    }

    @Override
    public void queryAvailByHolderId(String holderId, DataListener<CtmResult> listener) {

    }

    @Override
    public void uploadImages(File zipImages, DataListener<CtmResult> listener) {

    }

    @Override
    public void regist(RegistData registData, DataListener<CtmResult> listener) {

    }
}
