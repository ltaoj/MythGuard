package cn.ltaoj.mythguard.mvp.model.impl;

import cn.ltaoj.mythguard.bean.RegistData;
import cn.ltaoj.mythguard.cache.file.XmlFileCache;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.RegistModel;

/**
 * Created by ltaoj on 2018/3/14 17:11.
 */

public class RegistModelimpl implements RegistModel {

    private final String fileName = "regist";

    @Override
    public void saveRegistType(String type) {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .putString("type", type)
                .commit();
    }

    @Override
    public String getRegistType() {
        XmlFileCache cache = XmlFileCache.getInstance().startRead(fileName, XmlFileCache.MODE_PRIVATE);
        String type = cache.getString("type", "住户");
        return type;
    }

    @Override
    public void clearRegistType() {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE).getEditor()
                .remove("type")
                .commit();
    }

    @Override
    public void saveStepOne(String name, String ID, String phone) {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .putString("uname", name)
                .putString("idNumber", ID)
                .putString("phone", phone)
                .commit();
    }

    @Override
    public void clearStepOne() {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE).getEditor()
                .remove("uname")
                .remove("idNumber")
                .remove("phone")
                .commit();
    }

    @Override
    public void saveStepTwo(String facePath) {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .putString("facePath", facePath)
                .commit();
    }

    @Override
    public void clearStepTwo() {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE).getEditor()
                .remove("facePath")
                .commit();
    }

    @Override
    public void saveStepThrHouseNum(String houseNum) {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .putString("houseNumber", houseNum)
                .commit();
    }

    @Override
    public void saveStepThrHolderId(String holderId) {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .putString("binderId", holderId)
                .commit();
    }

    @Override
    public void clearStepThr() {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE).getEditor()
                .remove("houseNumber")
                .remove("binderId")
                .commit();
    }

    @Override
    public void loadRegistData(DataListener<RegistData> listener) {
        XmlFileCache cache = XmlFileCache.getInstance().startRead(fileName, XmlFileCache.MODE_PRIVATE);
        String uname = cache.getString("uname", "");
        String IDNumber = cache.getString("idNumber", "");
        String phone = cache.getString("phone", "");
        String facePath = cache.getString("facePath", "");
        String type = cache.getString("type", "");
        String houseNumber = cache.getString("houseNumber", "");
        String binderId = cache.getString("binderId", "");
        RegistData registData = uname.equals("") ? null : new RegistData(uname, IDNumber, phone, facePath, type, houseNumber, binderId);
        listener.onComplete(registData);
    }

    @Override
    public void clear() {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .clear()
                .commit();
    }
}
