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
    protected Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    // 初始化控件
    protected abstract void initView();
    // 初始化数据
    protected abstract void initData();
    // 统一初始化工具栏
    protected void initToolbar() {
        toolbar = this.findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    /**
     * activity跳转
     * @param clazz
     * @param closeCurrent
     * @param bundle 携带的数据
     */
    protected void jumpToActivity(Class clazz, boolean closeCurrent, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
        if (closeCurrent) {
            finish();
        }
    }

    protected abstract T createPresenter();
}
