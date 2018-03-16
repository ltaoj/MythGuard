package cn.ltaoj.mythguard.cache.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;

import java.lang.reflect.Field;

import cn.ltaoj.mythguard.bean.Signon;

/**
 * Created by ltaoj on 2018/3/14 14:18.
 * 本地文件xml存储
 * 基于SharedPreference实现
 * 单例类，应用启动时初始化
 */

public class XmlFileCache {

    private static XmlFileCache mInstance = null;

    private Context mContext = null;

    private SharedPreferences mSharedPreferences = null;

    private SharedPreferences.Editor mEditor = null;

    /**
     * 应用私有
     */
    public static final int MODE_PRIVATE = 0x0000;

    /**
     * 其他应用可读
     */
    public static final int MODE_WORLD_READABLE = 0x0001;

    /**
     * 其他应用可写
     */
    public static final int MODE_WORLD_WRITEABLE = 0x0002;

    /**
     * 允许多进程同时处理
     */
    public static final int MODE_MULTI_PROCESS = 0x0004;

    private XmlFileCache(Context context) {
        mContext = context;
    }

    /**
     * 单例类的初始化方法，单例类只能初始化一次，之后进行初始化将不会起作用
     * @param context
     */
    public static void init(Context context) {
        if (mInstance == null) {
            synchronized (XmlFileCache.class) {
                if (mInstance == null) {
                    mInstance = new XmlFileCache(context);
                }
            }
        }
    }

    /**
     * 获取对象实例，该方法必须在init方法之后执行
     * @return
     */
    public static XmlFileCache getInstance() {
        if (mInstance == null)
            throw new ExceptionInInitializerError("该类还未进行初始化，请先初始化该类");

        return mInstance;
    }

    /**
     * 打开mSharedPreference
     * @param name 文件名
     * @param mode 文件操作模式
     * @return
     */
    public XmlFileCache startWrite(String name, int mode) {
        mSharedPreferences = mContext.getSharedPreferences(name, mode);
        mEditor = mSharedPreferences.edit();
        return mInstance;
    }

    public XmlFileCache startRead(String name, int mode) {
        mSharedPreferences = mContext.getSharedPreferences(name, mode);
        return mInstance;
    }

    public XmlFileCache putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        return mInstance;
    }

    public boolean getBoolean(String key, boolean def) {
        return mSharedPreferences.getBoolean(key, def);
    }

    public XmlFileCache putInt(String key, int value) {
        mEditor.putInt(key, value);
        return mInstance;
    }

    public int getInt(String key, int def) {
        return mSharedPreferences.getInt(key, def);
    }

    public XmlFileCache putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        return mInstance;
    }

    public float getFloat(String key, float def) {
        return mSharedPreferences.getFloat(key, def);
    }

    public XmlFileCache putString(String key, String value) {
        mEditor.putString(key, value);
        return mInstance;
    }

    public String getString(String key, String def) {
        return mSharedPreferences.getString(key, def);
    }

    public XmlFileCache putLong(String key, long value) {
        mEditor.putLong(key, value);
        return mInstance;
    }

    public long getLong(String key, long def) {
        return mSharedPreferences.getLong(key, def);
    }

    public XmlFileCache clear() {
        mEditor.clear();
        return mInstance;
    }

    public XmlFileCache commit() {
        mEditor.commit();
        return mInstance;
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }
}
