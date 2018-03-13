package cn.ltaoj.mythguard.mvp.model;

import java.util.List;

import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by lenovo on 2018/3/1.
 */

public interface MemberModel {
    /**
     * 存储
     * @param members
     */
    void saveMembers(List<MemberItem> members);

    /**
     * 从缓存读取
     * @param listener
     */
    void loadMembersFromCache(DataListener<List<MemberItem>> listener);
}
