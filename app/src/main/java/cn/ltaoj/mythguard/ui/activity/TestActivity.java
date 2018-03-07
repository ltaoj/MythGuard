package cn.ltaoj.mythguard.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.ltaoj.mythguard.MainActivity;
import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;
import cn.ltaoj.mythguard.ui.adapter.MemberItemAdapter;
import cn.ltaoj.widget.SwipeLayout;

/**
 * Created by ltaoj on 2018/3/6 13:23.
 */

public class TestActivity extends MVPBaseActivity {

    private MemberItemAdapter mAdapter;
    private List<MemberItem> memberList = new CopyOnWriteArrayList<>();
    private int layoutId = R.layout.activity_test;
    private int toolbarId = R.id.common_toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView recyclerView = this.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MemberItemAdapter(getApplicationContext(), memberList);
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
                Toast.makeText(getApplicationContext(), "打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose(SwipeLayout swipeLayout) {
                Toast.makeText(getApplicationContext(), "关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwiping(SwipeLayout swipeLayout) {
//                Toast.makeText(getApplicationContext(), "滑动", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartOpen(SwipeLayout swipeLayout) {
                Toast.makeText(getApplicationContext(), "开始打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartClose(SwipeLayout swipeLayout) {
                Toast.makeText(getApplicationContext(), "开始关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFrontLayout(int position) {
                jumpToActivity(MainActivity.class, true, null);
            }

            @Override
            public void onDelete(int position) {
                Toast.makeText(getApplicationContext(), "删除", Toast.LENGTH_SHORT).show();
                memberList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        for (int i = 0;i < 5;i++) {
            memberList.add(new MemberItem());
        }
    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected int getToobarId() {
        return toolbarId;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
