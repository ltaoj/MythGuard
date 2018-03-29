package cn.ltaoj.mythguard.network;

import java.io.File;

import cn.ltaoj.mythguard.bean.CtmResult;
import cn.ltaoj.mythguard.bean.RegistData;
import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by ltaoj on 2018/3/14 21:47.
 */

public interface RegistApi {

    /**
     * 通过门牌号查询是否可以注册
     * @param houseNum
     * @param listener
     */
    void queryAvailByHouseNum(String houseNum, DataListener<CtmResult> listener);

    /**
     * 查询户主编号是否存在，以及户主状态是否有权限使成员注册
     * @param holderId
     * @param listener
     */
    void queryAvailByHolderId(String holderId, DataListener<CtmResult> listener);

    /**
     * 上传图像数据
     * @param zipImages
     * @param listener 返回的数据中，如果成功应该包含文件在服务器的地址
     */
    void uploadImages(File zipImages, DataListener<CtmResult> listener);

    /**
     * 注册接口，提交注册数据
     * @param listener
     */
    void regist(RegistData registData, DataListener<CtmResult> listener);
}
