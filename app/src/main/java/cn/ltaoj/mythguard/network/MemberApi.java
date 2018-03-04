package cn.ltaoj.mythguard.network;

import java.lang.reflect.Member;
import java.util.List;

import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by lenovo on 2018/3/1.
 */

public interface MemberApi {

    /**
     * 网络请求获取成员列表
     * @param listener
     */
    public void fetchMembers(DataListener<List<Member>> listener);
}