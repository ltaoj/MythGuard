package cn.ltaoj.mythguard.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ltaoj on 2018/3/7 15:17.
 */

public class ToastUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast mToast = null;

    private static CharSequence lastContent = null;

    private static long lastShowTime = 0;

    private static Map<CharSequence, Long> map = new HashMap<>();

    private static Object synObj = new Object();

    public static void showToast(Context context, CharSequence content) {
        showToast(context, content, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(Context context, CharSequence content) {
        showToast(context, content, Toast.LENGTH_LONG);
    }

    private static void showToast(final Context context, final CharSequence content, final int duration) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            boolean update = false;

                            // 如果内容和上次显示的不相同，则取消当前的toast
                            if (content != null && !content.equals(lastContent)) {
                                if (!map.containsKey(content) || (System.currentTimeMillis() - map.get(content)) > (duration + 5000)) {
                                    if (mToast != null) {
                                        mToast.cancel();
                                    }
                                    mToast = Toast.makeText(context, content, duration);
                                    update = true;
                                }
                            } else if (System.currentTimeMillis() - lastShowTime > duration + 10000) {
                                // 如果系统当前时间和上次显示时间差值大于duration，那么才会重新创建一个相同的Toast
                                mToast = Toast.makeText(context, content, duration);
                                update = true;
                            }

                            if (update) {
                                mToast.show();
                                lastContent = content;
                                lastShowTime = System.currentTimeMillis();
                                if (map.containsKey(lastContent)) {
                                    map.remove(lastContent);
                                }
                                map.put(lastContent, lastShowTime);
                            }
                        }
                    }
                });
            }
        }).start();
    }
}
