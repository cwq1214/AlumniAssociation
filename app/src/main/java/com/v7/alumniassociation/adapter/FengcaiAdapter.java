package com.v7.alumniassociation.adapter;

import android.view.ViewGroup;

import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;
import com.v7.alumniassociation.viewholder.BaseViewHolder;
import com.v7.alumniassociation.viewholder.FeangcaiViewHolder;

/**
 * Created by v7 on 2016/11/13.
 */

public class FengcaiAdapter extends BaseRefreshRcvAdapter {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeangcaiViewHolder viewHolder = new FeangcaiViewHolder(parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(dataList.get(position),position);
    }
}
