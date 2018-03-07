package cn.ltaoj.mythguard.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ltaoj on 2018/3/7 15:17.
 */

public class ToastUtil {

    public static void showToast(Context context, CharSequence content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, CharSequence content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }
}
