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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;

import cn.ltaoj.mythguard.ui.fragment.AddFragment;
import cn.ltaoj.mythguard.ui.fragment.MemberFragment;
import cn.ltaoj.mythguard.ui.fragment.OpendoorFragment;
import cn.ltaoj.mythguard.ui.fragment.VisitorFragment;
import cn.ltaoj.mythguard.util.ToastUtil;

public class MainActivity extends MVPBaseActivity {
    private static final String TAG = "MainActivity";
    
    private String[] tabs = new String[]{"家庭成员", "访客信息", "添加", "开门"};
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NavigationView navigationView;
    private final int layoutId = R.layout.activity_main;
    private final int toobarId  = R.id.main_toolbar;

    // 表示是否可以退出程序
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected int getToobarId() {
        return toobarId;
    }

    @Override
    protected void initView() {
        findViewById(R.id.ll_menu).setOnClickListener(this);
        initTab();
        initViewPager();
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        /*此处如果绑定了Toobar，那么会在Toolbar左边默认添加一个按钮*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initViewPager() {
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
    }

    private void initTab() {
        tabLayout = findViewById(R.id.tab_layout);
        Fragment mMemberFragment = new MemberFragment();
        Fragment mVisitorFragment = new VisitorFragment();
        Fragment mAddFragment = new AddFragment();
        Fragment mOpendoorFragment = new OpendoorFragment();
        fragments.add(mMemberFragment);
        fragments.add(mVisitorFragment);
        fragments.add(mAddFragment);
        fragments.add(mOpendoorFragment);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed: ");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByDoubleClick();
        }
        return false;
    }

    private void exitByDoubleClick() {
        Timer timer = null;
        if (!isExit) {
            isExit = true;
            ToastUtil.showToast(this, "请再按一次退出程序");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 2秒内没有再次按回退键，取消退出
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
