package cn.ltaoj.mythguard.mvp.model;

import java.lang.reflect.Member;
import java.util.List;

import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by lenovo on 2018/3/1.
 */

public interface MemberModel {
    /**
     * 存储
     * @param members
     */
    public void saveMembers(List<Member> members);

    /**
     * 从缓存读取
     * @param listener
     */
    public void loadMembersFromCache(DataListener<List<Member>> listener);
}
