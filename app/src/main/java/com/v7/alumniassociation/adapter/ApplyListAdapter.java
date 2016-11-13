package com.v7.alumniassociation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.v7.alumniassociation.bean.ApplyItemBean;
import com.v7.alumniassociation.viewholder.ApplyItemViewHolder;
import com.v7.alumniassociation.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by v7 on 2016/11/13.
 */

public class ApplyListAdapter extends RecyclerView.Adapter {
    ApplyItemViewHolder.OnAgreeClickListener onAgreeClickListener;

    List<ApplyItemBean> been;

    public void setBeen(List<ApplyItemBean> been) {
        this.been = been;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ApplyItemViewHolder viewHolder = new ApplyItemViewHolder(parent);
        if (onAgreeClickListener!=null)
        viewHolder.setOnAgreeClickListener(onAgreeClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).onBind(been.get(position),position);
    }

    @Override
    public int getItemCount() {
        if (been!=null)
            return been.size();
        return 0;
    }

    public void setOnAgreeClickListener(ApplyItemViewHolder.OnAgreeClickListener onAgreeClickListener) {
        this.onAgreeClickListener = onAgreeClickListener;
    }
}
