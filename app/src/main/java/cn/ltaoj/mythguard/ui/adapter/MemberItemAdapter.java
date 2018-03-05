package cn.ltaoj.mythguard.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.ltaoj.circleimageview.CircleImageView;
import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.bean.MemberItem;
import cn.ltaoj.widget.SwipeLayout;

/**
 * Created by ltaoj on 2018/3/6 3:07.
 */

public class MemberItemAdapter extends RecyclerView.Adapter<MemberItemAdapter.ViewHolder> {

    public interface OnMemberItemClickListener {

        void onOpen(SwipeLayout swipeLayout);

        void onClose(SwipeLayout swipeLayout);

        void onSwiping(SwipeLayout swipeLayout);

        void onStartOpen(SwipeLayout swipeLayout);

        void onStartClose(SwipeLayout swipeLayout);

        void onFrontLayout(int position);

        void onDelete(int position);
    }

    private OnMemberItemClickListener onMemberItemClickListener;
    private List<MemberItem> memberList;
    private Context context;
    private SwipeLayout preLayout;

    public MemberItemAdapter(Context context, List<MemberItem> memberList) {
        this.context = context;
        this.memberList = memberList;
    }

    public void setOnMemberItemClickListener(OnMemberItemClickListener onMemberItemClickListener) {
        this.onMemberItemClickListener = onMemberItemClickListener;
    }

    public SwipeLayout getPreLayout() {
        return preLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_member_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.memberAvatar.setImageResource(R.mipmap.iv_default_avatar);
        holder.textName.setText("李涛江");
        holder.textInTime.setText("刚刚");
        holder.textOutTime.setText("3天前");

        holder.swipeLayout.setOnSwipeChangeLintener(new SwipeLayout.OnSwipeChangeLintener() {
            @Override
            public void onStartOpen(SwipeLayout swipeLayout) {
                if (preLayout != swipeLayout) {
                    preLayout.close();
                }

                if (onMemberItemClickListener != null) {
                    onMemberItemClickListener.onStartOpen(swipeLayout);
                }
            }

            @Override
            public void onOpen(SwipeLayout swipeLayout) {
                preLayout = swipeLayout;

                if (onMemberItemClickListener != null) {
                    onMemberItemClickListener.onStartOpen(swipeLayout);
                }
            }

            @Override
            public void onStartClose(SwipeLayout swipeLayout) {
                if (onMemberItemClickListener != null) {
                    onMemberItemClickListener.onStartClose(swipeLayout);
                }
            }

            @Override
            public void onClose(SwipeLayout swipeLayout) {
                if (onMemberItemClickListener != null) {
                    onMemberItemClickListener.onClose(swipeLayout);
                }
            }

            @Override
            public void onSwiping(SwipeLayout swipeLayout) {
                if (onMemberItemClickListener != null) {
                    onMemberItemClickListener.onSwiping(swipeLayout);
                }
            }
        });

        holder.frontLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipeLayout.getSwipState() == SwipeLayout.SwipeState.OPEN) {
                    holder.swipeLayout.close();
                } else if (onMemberItemClickListener != null){
                    onMemberItemClickListener.onFrontLayout(position);
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMemberItemClickListener != null) {
                    onMemberItemClickListener.onDelete(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SwipeLayout swipeLayout;
        private TextView delete;
        private ConstraintLayout frontLayout;
        private CircleImageView memberAvatar;
        private TextView textName;
        private TextView textLastIn;
        private TextView textInTime;
        private TextView textLastOut;
        private TextView textOutTime;


        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            delete = itemView.findViewById(R.id.delete);
            frontLayout = itemView.findViewById(R.id.front_layout);
            memberAvatar = itemView.findViewById(R.id.member_avatar);
            textName = itemView.findViewById(R.id.text_name);
            textLastIn = itemView.findViewById(R.id.text_last_in);
            textInTime = itemView.findViewById(R.id.text_in_time);
            textLastOut = itemView.findViewById(R.id.text_last_out);
            textOutTime = itemView.findViewById(R.id.text_out_time);
        }
    }
}
