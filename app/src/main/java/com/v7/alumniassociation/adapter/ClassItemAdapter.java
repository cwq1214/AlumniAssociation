package com.v7.alumniassociation.adapter;

import android.view.ViewGroup;

import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;
import com.v7.alumniassociation.viewholder.BBSPostItemViewHolder;
import com.v7.alumniassociation.viewholder.BaseViewHolder;
import com.v7.alumniassociation.viewholder.ClassItemViewHolder;

/**
 * Created by v7 on 2016/11/13.
 */

public class ClassItemAdapter extends BaseRefreshRcvAdapter {
    ClassItemViewHolder.OnItemClickListener onItemClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClassItemViewHolder viewHolder = new ClassItemViewHolder(parent);
        viewHolder.setOnItemClickListener(onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(dataList.get(position),position);
    }

    public void setOnItemClickListener(ClassItemViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
