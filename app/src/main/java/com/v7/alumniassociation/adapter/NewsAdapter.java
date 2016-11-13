package com.v7.alumniassociation.adapter;

import android.view.ViewGroup;

import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;
import com.v7.alumniassociation.viewholder.BaseViewHolder;
import com.v7.alumniassociation.viewholder.NewsViewHolder;

/**
 * Created by v7 on 2016/11/11.
 */

public class NewsAdapter extends BaseRefreshRcvAdapter {

    NewsViewHolder.OnItemClickListener onItemClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsViewHolder holder = new NewsViewHolder(parent);
        if (onItemClickListener!=null)
            holder.setOnItemClickListener(onItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(dataList.get(position),position);
    }

    public void setOnItemClickListener(NewsViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
