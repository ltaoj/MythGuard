package cn.ltaoj.mythguard.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by ltaoj on 2018/3/7 17:42.
 */

public class BasicListItem {

    private Drawable drawable;
    private String key;
    private String value;

    public BasicListItem(Drawable drawable, String key, String value) {
        this.drawable = drawable;
        this.key = key;
        this.value = value;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
