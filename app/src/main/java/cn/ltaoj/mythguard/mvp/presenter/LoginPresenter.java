package cn.ltaoj.mythguard.mvp.presenter;

import cn.ltaoj.mythguard.bean.Signon;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.RegistModel;
import cn.ltaoj.mythguard.mvp.model.SignonModel;
import cn.ltaoj.mythguard.mvp.model.impl.RegistModelimpl;
import cn.ltaoj.mythguard.mvp.model.impl.SignonModelimpl;
import cn.ltaoj.mythguard.mvp.view.ILoginView;
import cn.ltaoj.mythguard.network.AccountApi;
import cn.ltaoj.mythguard.network.impl.AccountApiimpl;

/**
 * Created by ltaoj on 2018/3/13 14:43.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    public static final int HOLDER_REGIST = 0;
    public static final int MEMBER_REGIST = 1;
    public static final int VISITOR_REGIST = 2;

    private ILoginView mLoginView;

    private SignonModel mSignonModel = new SignonModelimpl();

    private AccountApi mAccounrApi = new AccountApiimpl();

    private RegistModel mRegistModel = new RegistModelimpl();

    public LoginPresenter(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
    }

    /**
     * 自动登录
     */
    public void autoLogin() {
        mLoginView.showLoading();
        mSignonModel.loadSiFromLocalDB(new DataListener<Signon>() {
            @Override
            public void onComplete(Signon result) {
                mLoginView.hideLoading();
                if (result == null || !result.isAuth()) return;

                mLoginView.jumpToHome();
            }
        });
    }

    /**
     * 当点击登录按钮时调用的登录方法
     * @return 登录成功返回1，失败返回0
     */
    public void login() {
        final String account = mLoginView.getAccount();
        String pwd = mLoginView.getPwd();

        // 如果之前提示还存在，先关闭
        mLoginView.hideTip();
        if (account == null || account.trim().equals("")) {
            mLoginView.showTip(ILoginView.InputType.ACCOUNT);
            return;
        } else if (pwd == null || pwd.trim().equals("")) {
            mLoginView.showTip(ILoginView.InputType.PWD);
            return;
        }

        // 调用登录api
        mLoginView.showLoading();
        mAccounrApi.login(account, pwd, new DataListener<Signon>() {
            @Override
            public void onComplete(Signon result) {
                mLoginView.hideLoading();
                if (result == null){ // 结果为空，表示登录验证失败
                    mLoginView.showResult("登录失败,请重试");
                    mLoginView.clearInput();
                } else {
                    mSignonModel.saveSignonInfo(result);
                    mLoginView.showResult("登陆成功");
                    mLoginView.jumpToHome();
                }
            }
        });
    }

    /**
     * 保存注册类型，跳转注册页面
     * @param type
     */
    public void goToRegist(int type) {
        switch (type) {
            case HOLDER_REGIST:
                mRegistModel.saveRegistType("户主");
                mLoginView.jumpToRegist();
                break;
            case MEMBER_REGIST:
                mRegistModel.saveRegistType("常驻住户");
                mLoginView.jumpToRegist();
                break;
            case VISITOR_REGIST:
                mRegistModel.saveRegistType("访客");
                break;
            default:
                break;
        }
    }
}
