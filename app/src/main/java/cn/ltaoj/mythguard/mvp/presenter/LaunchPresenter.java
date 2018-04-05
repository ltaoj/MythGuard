package cn.ltaoj.mythguard.mvp.presenter;

import cn.ltaoj.mythguard.bean.Signon;
import cn.ltaoj.mythguard.listener.DataListener;
import cn.ltaoj.mythguard.mvp.model.RegistModel;
import cn.ltaoj.mythguard.mvp.model.SignonModel;
import cn.ltaoj.mythguard.mvp.model.impl.RegistModelimpl;
import cn.ltaoj.mythguard.mvp.model.impl.SignonModelimpl;
import cn.ltaoj.mythguard.mvp.view.ILaunchView;

/**
 * Created by ltaoj on 2018/3/31 20:20.
 */

public class LaunchPresenter extends BasePresenter<ILaunchView> {

    public static final int HOLDER_REGIST = 0;
    public static final int MEMBER_REGIST = 1;
    public static final int VISITOR_REGIST = 2;

    private ILaunchView mLaunchView;

    private RegistModel mRegistModel = new RegistModelimpl();
    private SignonModel mSignonModel = new SignonModelimpl();

    public LaunchPresenter(ILaunchView launchView) {
        this.mLaunchView = launchView;
    }

    /**
     * 保存注册类型，跳转注册页面
     * @param type
     */
    public void goToRegist(int type) {
        switch (type) {
            case HOLDER_REGIST:
                mRegistModel.saveRegistType("户主");
                mLaunchView.jumpToRegistActivity();
                break;
            case MEMBER_REGIST:
                mRegistModel.saveRegistType("常驻住户");
                mLaunchView.jumpToRegistActivity();
                break;
            case VISITOR_REGIST:
                mRegistModel.saveRegistType("访客");
                break;
            default:
                break;
        }
    }

    public void autoLogin() {
        mSignonModel.loadSiFromLocalDB(new DataListener<Signon>() {
            @Override
            public void onComplete(Signon result) {
                if (result == null || !result.isAuth()) return;

                mLaunchView.jumpToHomeActivity();
            }
        });
    }
}
