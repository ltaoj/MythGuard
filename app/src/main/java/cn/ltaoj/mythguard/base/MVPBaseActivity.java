package cn.ltaoj.mythguard.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;

/**
 * Created by lenovo on 2018/3/1.
 */

public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends RxAppCompatActivity implements View.OnClickListener {

    // Presenter对象
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView((V) this);
        setSystemUI();
        setContentView(getLayoutId());
        initToolbar();
        initView();
        initData();
    }

    // 提供一个空实现，如果对系统状态栏等有要求，重载此方法
    protected void setSystemUI() {}
    // 初始化控件
    protected abstract void initView();
    // 初始化数据
    protected abstract void initData();
    // 统一初始化工具栏, 如果Activity使用了其他布局，那么需要重载此方法
    protected void initToolbar() {
        Toolbar toolbar = this.findViewById(getToobarId());
        setSupportActionBar(toolbar);
    }
    // 如果工具栏使用其他id，那么重载此方法
    protected int getToobarId() {
        return R.id.common_toolbar;
    }

    // Activity资源布局接口
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    /**
     * activity跳转
     * @param clazz
     * @param closeCurrent
     * @param bundle 携带的数据
     */
    protected void jumpToActivity(Class clazz, boolean closeCurrent, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }

        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
        if (closeCurrent) {
            finish();
        }
    }

    protected abstract T createPresenter();
}
