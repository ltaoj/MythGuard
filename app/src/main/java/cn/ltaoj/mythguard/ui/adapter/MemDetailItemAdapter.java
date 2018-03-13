package cn.ltaoj.mythguard.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.ltaoj.mythguard.R;
import cn.ltaoj.mythguard.bean.BasicListItem;

/**
 * Created by ltaoj on 2018/3/7 17:24.
 */

public class MemDetailItemAdapter extends RecyclerView.Adapter<MemDetailItemAdapter.ViewHolder> {
    private static final String TAG = "MemDetailItemAdapter";

    private Context mContext;
    private List<BasicListItem> mData;
    private OnItemClickListener onItemClickListener;

    public MemDetailItemAdapter(Context mContext, List<BasicListItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    // 预留接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.icon.setImageDrawable(mData.get(position).getDrawable());
        holder.keyName.setText(mData.get(position).getKey());
        holder.value.setText(mData.get(position).getValue());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout root;
        private ImageView icon;
        private TextView keyName;
        private TextView value;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_root);
            icon = itemView.findViewById(R.id.icon);
            keyName = itemView.findViewById(R.id.key_name);
            value = itemView.findViewById(R.id.value);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view);
    }

    public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    }
}
