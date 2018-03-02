package cn.ltaoj.mythguard.mvp.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2018/3/1.
 */

public abstract class BasePresenter<T> {

    // view接口类型的弱引用
    protected Reference<T> mViewRef;

    // 建立Presenter和view的关联
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    // 取消Presenter和view的关联
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
