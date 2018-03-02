package cn.ltaoj.mythguard.mvp.presenter;

import java.lang.reflect.Member;
import java.util.List;

import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.MemberModel;
import cn.ltaoj.mythguard.mvp.model.impl.MemberModelimpl;
import cn.ltaoj.mythguard.mvp.view.IMemberView;
import cn.ltaoj.mythguard.network.MemberApi;
import cn.ltaoj.mythguard.network.impl.MemberApiimpl;

/**
 * Created by lenovo on 2018/3/1.
 */

public class MemberPresenter extends BasePresenter {

    // IMemberView接口，代表了View接口角色
    IMemberView mMemberView;
    // 成员数据的Model，代表Model角色
    MemberModel mMemberModel = new MemberModelimpl();
    // 从网络上获取成员的API
    MemberApi mMemberApi = new MemberApiimpl();

    public MemberPresenter(IMemberView memberView) {
        this.mMemberView = memberView;
    }

    // 获取成员，也就是业务逻辑函数
    public void fetchMembers() {
        mMemberView.showLoading();
        mMemberApi.fetchMembers(new DataListener<List<Member>>() {
            @Override
            public void onComplete(List<Member> result) {
                mMemberView.showMembers(result);
                mMemberView.hideLoading();
                mMemberModel.saveMembers(result);
            }
        });
    }

    // 获取成员，从数据库获取
    public void loadMembersFromDB() {
        mMemberModel.loadMembersFromCache(new DataListener<List<Member>>() {
            @Override
            public void onComplete(List<Member> result) {
                mMemberView.showMembers(result);
            }
        });
    }
}
