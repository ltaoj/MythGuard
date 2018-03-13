package cn.ltaoj.mythguard.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseFragment;
import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;
import cn.ltaoj.mythguard.mvp.presenter.MemberPresenter;
import cn.ltaoj.mythguard.mvp.view.IMemberView;
import cn.ltaoj.mythguard.ui.activity.MemDetailActivity;
import cn.ltaoj.mythguard.ui.adapter.MemberItemAdapter;
import cn.ltaoj.widget.SwipeLayout;

/**
 * Created by lenovo on 2018/3/2.
 */

public class MemberFragment extends MVPBaseFragment implements IMemberView {

    private int layoutId = R.layout.fragment_member;
    private MemberItemAdapter mAdapter;
    private List<MemberItem> memberList = new CopyOnWriteArrayList<>();

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView recyclerView = getRootView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        // 设置默认分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new MemberItemAdapter(getContext(), memberList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    SwipeLayout preLayout = mAdapter.getPreLayout();
                    if (preLayout != null) {
                        preLayout.close();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mAdapter.setOnMemberItemClickListener(new MemberItemAdapter.OnMemberItemClickListener() {
            @Override
            public void onOpen(SwipeLayout swipeLayout) {
                Toast.makeText(getContext(), "打开", Toast.LENGTH_SHORT);
            }

            @Override
            public void onClose(SwipeLayout swipeLayout) {
                Toast.makeText(getContext(), "关闭", Toast.LENGTH_SHORT);
            }

            @Override
            public void onSwiping(SwipeLayout swipeLayout) {
                Toast.makeText(getContext(), "滑动", Toast.LENGTH_SHORT);
            }

            @Override
            public void onStartOpen(SwipeLayout swipeLayout) {
                Toast.makeText(getContext(), "开始打开", Toast.LENGTH_SHORT);
            }

            @Override
            public void onStartClose(SwipeLayout swipeLayout) {
                Toast.makeText(getContext(), "开始关闭", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFrontLayout(int position) {
                jumpToActivity(MemDetailActivity.class, null);
            }

            @Override
            public void onDelete(int position) {
                memberList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {
        for (int i = 0;i < 5;i++) {
            memberList.add(new MemberItem());
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MemberPresenter(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showMembers(List<MemberItem> members) {
        memberList.clear();
        memberList.addAll(members);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(), "显示加载动画", Toast.LENGTH_SHORT);
    }

    @Override
    public void hideLoading() {
        Toast.makeText(getContext(), "隐藏加载动画", Toast.LENGTH_SHORT);
    }
}
