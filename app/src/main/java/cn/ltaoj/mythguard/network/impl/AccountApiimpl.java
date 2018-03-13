package cn.ltaoj.mythguard.network.impl;

import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.bean.Signon;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.network.AccountApi;

/**
 * Created by ltaoj on 2018/3/13 15:13.
 */

public class AccountApiimpl implements AccountApi {

    @Override
    public void login(String account, String pwd, DataListener<Signon> listener) {
        listener.onComplete(auth());
    }

    @Override
    public void getUserInfo(String account, DataListener<MemberItem> listener) {

    }

    private Signon auth() {
        // 网络上验证后，如果验证成功，返回signon对象，如果验证失败返回null
        return new Signon("123456", true);
    }
}
