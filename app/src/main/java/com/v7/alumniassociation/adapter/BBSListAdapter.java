package com.v7.alumniassociation.adapter;

import android.view.ViewGroup;

import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;
import com.v7.alumniassociation.viewholder.BBSPostItemViewHolder;
import com.v7.alumniassociation.viewholder.BaseViewHolder;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSListAdapter extends BaseRefreshRcvAdapter{

    BBSPostItemViewHolder.OnItemClickListener onItemClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BBSPostItemViewHolder viewHolder = new BBSPostItemViewHolder(parent);
        if (onItemClickListener!=null)
            viewHolder.setOnItemClickListener(onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(dataList.get(position),position);
    }

    public void setOnItemClickListener(BBSPostItemViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
