package cn.ltaoj.mythguard.bean;

import com.android.volley.NetworkResponse;

/**
 * Created by ltaoj on 2018/3/29 12:34.
 * 用于封装二元结果，及其原因
 */

public class CtmResult {
    // true表示想要的结果，false表示反面结果
    private boolean success;
    // 结果状态码
    private int code;
    // 产生结果原因
    private String msg;
    // 返回结果中包含的url
    private String url;

    public CtmResult() {
    }

    public CtmResult(boolean success, int code, String msg, String url) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.url = url;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public interface Code {
        int REQUEST_ERROR = -1; // 网络请求错误
    }

    public static CtmResult getResultByNetResponse(NetworkResponse networkResponse) {
        CtmResult ctmResult = null;
        if (networkResponse == null) {
            ctmResult = new CtmResult(false, CtmResult.Code.REQUEST_ERROR, "数据接口错误", "");
        } else {
            int statusCode = networkResponse.statusCode;
            switch (statusCode) {
                case 405:
                    ctmResult = new CtmResult(false, CtmResult.Code.REQUEST_ERROR, "请求方法错误", "");
                    break;
                case 404:
                    ctmResult = new CtmResult(false, CtmResult.Code.REQUEST_ERROR, "接口不存在", "");
                    break;
                case 415:
                    ctmResult = new CtmResult(false, CtmResult.Code.REQUEST_ERROR, "请求参数错误", "");
                    break;
                case 500:
                    ctmResult = new CtmResult(false, CtmResult.Code.REQUEST_ERROR, "服务器错误", "");
                    break;
                default:
                    ctmResult = new CtmResult(false, CtmResult.Code.REQUEST_ERROR, "请求出错", "");
                    break;
            }
        }
        return ctmResult;
    }
}
