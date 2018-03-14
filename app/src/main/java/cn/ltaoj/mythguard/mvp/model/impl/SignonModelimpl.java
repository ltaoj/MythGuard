package cn.ltaoj.mythguard.mvp.model.impl;

import cn.ltaoj.mythguard.bean.Signon;
import cn.ltaoj.mythguard.cache.file.XmlFileCache;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.SignonModel;

/**
 * Created by ltaoj on 2018/3/13 14:57.
 */

public class SignonModelimpl implements SignonModel {

    private final String fileName = "signon";

    @Override
    public void saveSignonInfo(Signon signon) {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .putString("account", signon.getAccount())
                .putBoolean("auth", signon.isAuth())
                .commit();
    }

    @Override
    public void loadSiFromLocalDB(DataListener<Signon> listener) {
        XmlFileCache cache = XmlFileCache.getInstance().startRead(fileName, XmlFileCache.MODE_PRIVATE);
        String account = cache.getString("account", "");
        Signon signon = account.equals("") ? null : new Signon(account, cache.getBoolean("auth", false));
        listener.onComplete(signon);
    }

    @Override
    public void clearSignonInfo() {
        XmlFileCache.getInstance().startWrite(fileName, XmlFileCache.MODE_PRIVATE)
                .clear()
                .commit();
    }
}
