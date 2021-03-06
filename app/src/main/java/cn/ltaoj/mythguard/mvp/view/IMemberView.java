package cn.ltaoj.mythguard.mvp.view;

import java.lang.reflect.Member;
import java.util.List;

import cn.ltaoj.mythguard.bean.MemberItem;

/**
 * Created by lenovo on 2018/3/1.
 */

public interface IMemberView {

    /**
     * 展示数据
     * @param members
     */
    public void showMembers(List<MemberItem> members);

    /**
     * 显示进度条
     */
    public void showLoading();

    /**
     * 隐藏进度条
     */
    public void hideLoading();
}
