package cn.ltaoj.mythguard.network;

import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.bean.Signon;
import cn.ltaoj.mythguard.listener.DataListener;

/**
 * Created by ltaoj on 2018/3/13 15:05.
 */

public interface AccountApi {

    /**
     * 用户名 + 密码登录
     * @param account
     * @param pwd
     * @param listener 监听登录状态, 登录成功返回用户信息，失败则为null
     */
    void login(String account, String pwd, DataListener<Signon> listener);

    /**
     * 根据用户账户获取用户资料
     * @param account
     * @param listener
     */
    void getUserInfo(String account, DataListener<MemberItem> listener);
}
