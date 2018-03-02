package cn.ltaoj.mythguard.network.impl;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.network.MemberApi;

/**
 * Created by lenovo on 2018/3/1.
 */

public class MemberApiimpl implements MemberApi {
    @Override
    public void fetchMembers(DataListener<List<Member>> listener) {
        List<Member> memberList = new ArrayList<>();
        /**
         * 请求网络填充memberList列表
         */
        listener.onComplete(memberList);
    }
}
