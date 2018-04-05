package cn.ltaoj.mythguard.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.PopupWindow;

import cn.ltaoj.mythguard.R;

/**
 * Created by ltaoj on 2018/3/31 21:02.
 */

public class PopMenu extends PopupWindow implements View.OnClickListener {

    private View mView;

    private Context mContext;

    private ItemClickListener mItemClickListener;

    public PopMenu(Context context, View parent, ItemClickListener listener) {
        super(context);
        this.mContext = context;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(false);
        setTouchable(true);
        mView = LayoutInflater.from(context).inflate(R.layout.widget_pop_menu, null);
        setContentView(mView);
        // 设置动画必须在show之前
        setAnimationStyle(R.style.popmenu_anim_style);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
        this.mItemClickListener = listener;
        View layout = mView.findViewById(R.id.btns_layout);
        Rect rect = new Rect();
        layout.getDrawingRect(rect);
        int height = context.getResources().getDisplayMetrics().heightPixels;

        ObjectAnimator.ofFloat(layout, "translationY", height, rect.top).setDuration(500).start();

        mView.findViewById(R.id.btn_holder).setOnClickListener(this);
        mView.findViewById(R.id.btn_member).setOnClickListener(this);
        mView.findViewById(R.id.btn_visitor).setOnClickListener(this);
        mView.findViewById(R.id.close).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_holder:
                mItemClickListener.performHolderRegist();
                break;
            case R.id.btn_member:
                mItemClickListener.performMemberRegist();
                break;
            case R.id.btn_visitor:
                mItemClickListener.performVisitorRegist();
                break;
            case R.id.close:
                if (isShowing()) {
                    View layout = mView.findViewById(R.id.btns_layout);
                    Rect rect = new Rect();
                    layout.getDrawingRect(rect);
                    int height = mContext.getResources().getDisplayMetrics().heightPixels;
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layout, "translationY", rect.top, height);
                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            dismiss();
                        }
                    });
                    objectAnimator.setDuration(300);
                    objectAnimator.start();
                }
                break;
        }
    }

    public interface ItemClickListener {

        void performHolderRegist();

        void performMemberRegist();

        void performVisitorRegist();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
