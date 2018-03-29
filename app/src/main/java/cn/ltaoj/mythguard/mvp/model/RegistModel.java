package cn.ltaoj.mythguard.mvp.model;

import cn.ltaoj.mythguard.bean.RegistData;
import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by ltaoj on 2018/3/14 17:11.
 */

public interface RegistModel {

    /**
     * 保存注册类型
     * @param type
     */
    void saveRegistType(String type);

    /**
     * 获得注册类型，住户注册，常驻住户注册，访客注册
     * @return
     */
    String getRegistType();

    /**
     * 清除注册类型
     */
    void clearRegistType();

    /**
     * 存储注册第一个页面数据
     */
    void saveStepOne(String name, String ID, String phone);

    /**
     * 清除第一个页面数据
     */
    void clearStepOne();

    /**
     * 存储注册第二个页面人脸数据
     * @param facePath
     */
    void saveStepTwo(String facePath);

    /**
     * 清除第二个页面数据
     */
    void clearStepTwo();

    /**
     * 保存第三步门牌号
     * @param houseNum
     */
    void saveStepThrHouseNum(String houseNum);

    /**
     * 保存第三步骤绑定者编号
     * @param holderId
     */
    void saveStepThrHolderId(String holderId);

    /**
     * 清除第三个页面缓存数据
     */
    void clearStepThr();

    /**
     * 从本地加载存储的注册数据
     */
    void loadRegistData(DataListener<RegistData> listener);

    /**
     * 清除注册产生的数据
     */
    void clear();
}
