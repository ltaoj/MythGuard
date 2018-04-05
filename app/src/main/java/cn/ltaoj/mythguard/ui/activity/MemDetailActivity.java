package cn.ltaoj.mythguard.ui.activity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.bean.BasicListItem;
import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;
import cn.ltaoj.mythguard.ui.adapter.MemDetailItemAdapter;

/**
 * Created by ltaoj on 2018/3/7 15:29.
 */

public class MemDetailActivity extends MVPBaseActivity {

    private final int layoutId = R.layout.activity_mem_detail;
    private MemDetailItemAdapter mAdapter;
    private final List<BasicListItem> listItems = new ArrayList<>();;
    private String[] keys = new String[] { "姓名", "证件", "电话", "门牌号" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("住户信息");
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView recyclerView = findViewById(R.id.info_list);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MemDetailItemAdapter(this, listItems);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        MemberItem memberItem = new MemberItem();
        memberItem.setUname("王铁柱");
        memberItem.setIdNumber("41078219990101XXXX");
        memberItem.setPhone("11188888888");
        memberItem.setHouseNumber("仙鹤小区15栋2单元1404");
        String[] values = new String[] {memberItem.getUname(), memberItem.getIdNumber(), memberItem.getPhone(), memberItem.getHouseNumber()};
        for (int i = 0;i < keys.length;i++) {
            BasicListItem item = new BasicListItem(null, keys[i], values[i]);
            listItems.add(item);
        }
    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
