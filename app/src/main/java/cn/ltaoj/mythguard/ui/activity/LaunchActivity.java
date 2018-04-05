package cn.ltaoj.mythguard.ui.activity;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import cn.ltaoj.mythguard.MainActivity;
import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.base.MVPBaseActivity;
import cn.ltaoj.mythguard.cache.file.XmlFileCache;
import cn.ltaoj.mythguard.mvp.presenter.LaunchPresenter;
import cn.ltaoj.mythguard.mvp.view.ILaunchView;
import cn.ltaoj.mythguard.widget.PopMenu;

/**
 * Created by ltaoj on 2018/3/31 20:20.
 */

public class LaunchActivity extends MVPBaseActivity<ILaunchView, LaunchPresenter> implements ILaunchView {
    private static final String TAG = "LaunchActivity";

    private final int layoutId = R.layout.activity_launch;

    private Button loginBtn = null;
    private Button registBtn = null;

    private PopMenu mPopMenu = null;

    private PopMenu.ItemClickListener mItemClickListener = new PopMenu.ItemClickListener() {
        @Override
        public void performHolderRegist() {
            mPopMenu.dismiss();
            mPresenter.goToRegist(LaunchPresenter.HOLDER_REGIST);
        }

        @Override
        public void performMemberRegist() {
            mPopMenu.dismiss();
            mPresenter.goToRegist(LaunchPresenter.MEMBER_REGIST);
        }

        @Override
        public void performVisitorRegist() {
            mPopMenu.dismiss();
            mPresenter.goToRegist(LaunchPresenter.VISITOR_REGIST);
        }
    };

    @Override
    protected void initView() {
        loginBtn = findViewById(R.id.to_login);
        registBtn = findViewById(R.id.to_regist);
        loginBtn.setOnClickListener(this);
        registBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        // 初始化XmlFileCache单例类, 该方法需要在使用presenter之前初始化，否则会发生异常
        XmlFileCache.init(this);
        mPresenter.autoLogin();
        showButtons();
    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected LaunchPresenter createPresenter() {
        return new LaunchPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_login:
                jumpToLoginActivity();
                break;
            case R.id.to_regist:
                showPopWnd();
                break;
        }
    }

    @Override
    public void showButtons() {
        Rect rect = new Rect();
        View loginBtn = findViewById(R.id.to_login);
        View registBtn = findViewById(R.id.to_regist);
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        loginBtn.getDrawingRect(rect);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(loginBtn, "translationY", screenHeight, rect.top);
        objectAnimator1.setInterpolator(new DecelerateInterpolator());
        objectAnimator1.setDuration(1000).start();
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(registBtn, "translationY", screenHeight, rect.top);
        objectAnimator2.setInterpolator(new DecelerateInterpolator());
        objectAnimator2.setDuration(1000).start();
    }

    @Override
    public void hideButtons() {
        Rect rect = new Rect();
        View loginBtn = findViewById(R.id.to_login);
        View registBtn = findViewById(R.id.to_regist);
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        loginBtn.getDrawingRect(rect);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(loginBtn, "translationY", rect.top, screenHeight);
        objectAnimator1.setInterpolator(new DecelerateInterpolator());
        objectAnimator1.setDuration(500).start();
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(registBtn, "translationY", rect.top, screenHeight);
        objectAnimator2.setInterpolator(new DecelerateInterpolator());
        objectAnimator2.setDuration(500).start();
    }

    @Override
    public void showPopWnd() {
        mPopMenu = new PopMenu(this, findViewById(R.id.root), mItemClickListener);
    }

    @Override
    public void hidePopWnd() {

    }

    @Override
    public void jumpToRegistActivity() {
        jumpToActivity(RegistActivity.class, false, null);
    }

    @Override
    public void jumpToLoginActivity() {
        jumpToActivity(LoginActivity.class, false, null);
    }

    @Override
    public void jumpToHomeActivity() {
        jumpToActivity(MainActivity.class, true, null);
    }
}
