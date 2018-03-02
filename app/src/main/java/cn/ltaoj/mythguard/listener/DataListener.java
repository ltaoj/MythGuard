package cn.ltaoj.mythguard.listener;

/**
 * Created by lenovo on 2018/3/1.
 * 数据获取接口
 */

public interface DataListener<T> {

    public void onComplete(T result);
}
