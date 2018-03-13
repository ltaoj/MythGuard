package cn.ltaoj.mythguard.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import cn.ltaoj.mythguard.mvp.presenter.BasePresenter;

/**
 * Created by lenovo on 2018/3/2.
 */

public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends RxFragment implements View.OnClickListener {

    private View mRootView = null;
    protected T mPresenter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = mRootView != null ? mRootView : inflater.inflate(getLayoutId(), null);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView((V) this);
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected View getRootView() {
        return mRootView;
    }

    /**
     * activity跳转
     * @param clazz
     * @param bundle 携带的数据
     */
    protected void jumpToActivity(Class clazz, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }

        Intent intent = new Intent(this.getContext(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * Fragment布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件、绑定事件
     */
    protected abstract void initView();

    /**
     * 初始化Fragment数据,网络请求、本地获取
     */
    protected abstract void initData();

    /**
     * 创建Presenter，如果不需要绑定Presenter对象，可以为空实现
     * @return
     */
    protected abstract T createPresenter();
}
