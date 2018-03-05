package cn.ltaoj.mythguard.mvp.model.impl;

import java.util.List;

import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.MemberModel;

/**
 * Created by lenovo on 2018/3/1.
 */

public class MemberModelimpl implements MemberModel {
    @Override
    public void saveMembers(List<MemberItem> members) {

    }

    @Override
    public void loadMembersFromCache(DataListener<List<MemberItem>> listener) {
        listener.onComplete(null);
    }
}
