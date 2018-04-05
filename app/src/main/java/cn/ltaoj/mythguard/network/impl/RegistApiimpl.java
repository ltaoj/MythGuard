package cn.ltaoj.mythguard.network.impl;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import cn.ltaoj.mythguard.base.MythApplication;
import cn.ltaoj.mythguard.bean.CtmResult;
import cn.ltaoj.mythguard.bean.RegistData;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.network.RegistApi;

/**
 * Created by ltaoj on 2018/3/14 21:47.
 */

public class RegistApiimpl implements RegistApi {
    private static final String REGIST_POST = "https://www.ltaoj.cn";

    @Override
    public void queryAvailByHouseNum(String houseNum, DataListener<CtmResult> listener) {
        CtmResult result = new CtmResult(true, 0, "未绑定", "");
        listener.onComplete(result);
    }

    @Override
    public void queryAvailByHolderId(String holderId, DataListener<CtmResult> listener) {

    }

    @Override
    public void uploadImages(File zipImages, DataListener<CtmResult> listener) {
        CtmResult result = new CtmResult(true, 0, "上传成功",
                "https://www.ltaoj.cn/images/unresolved/H1234567-2018-03-29.zip");
        listener.onComplete(result);
    }

    @Override
    public void regist(final RegistData registData, final DataListener<CtmResult> listener) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new Gson().toJson(registData));
            JsonRequest request = new JsonObjectRequest(Request.Method.POST, REGIST_POST, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    CtmResult ctmResult = new Gson().fromJson(response.toString(), CtmResult.class);
                    listener.onComplete(ctmResult);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
