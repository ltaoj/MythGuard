package cn.ltaoj.mythguard.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ltaoj on 2018/4/5 14:53.
 * 在全局Application中配置Volley请求队列
 */

public class MythApplication extends Application {

    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues() {
        return mRequestQueue;
    }
}
