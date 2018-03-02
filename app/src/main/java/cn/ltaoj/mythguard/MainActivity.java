package cn.ltaoj.mythguard;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;
import cn.ltaoj.mythguard.mvp.presenter.MemberPresenter;
import cn.ltaoj.mythguard.mvp.view.IMemberView;

public class MainActivity extends MVPBaseActivity implements IMemberView{

    private String[] tabs = new String[]{"家庭成员", "访客信息", "添加", "开门"};
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
        initData();
    }

    @Override
    protected void initView() {
        findViewById(R.id.ll_menu).setOnClickListener(this);
        initTab();
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(tabs.length);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }

            @Override
            public int getCount() {
                return tabs.length;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        /*此处如果绑定了Toobar，那么会在Toolbar左边默认添加一个按钮*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initTab() {
        tabLayout = findViewById(R.id.tab_layout);
        Fragment mMemberFragment = null;
        Fragment mVisitorFragment = null;
        Fragment mAddFragment = null;
        Fragment mOpendoorFragment = null;
        fragments.add(mMemberFragment);
        fragments.add(mVisitorFragment);
        fragments.add(mAddFragment);
        fragments.add(mOpendoorFragment);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return new MemberPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_menu:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
                break;
        }
    }

    @Override
    public void showMembers(List<Member> members) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
