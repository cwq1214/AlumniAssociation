package com.v7.alumniassociation.adapter;

import android.view.ViewGroup;

import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;
import com.v7.alumniassociation.viewholder.BaseViewHolder;
import com.v7.alumniassociation.viewholder.ImageViewHolder;

/**
 * Created by v7 on 2016/11/10.
 */

public class ClassImgAlbumAdapter extends BaseRefreshRcvAdapter {

    ImageViewHolder.OnImgClickListener onImgClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageViewHolder holder =  new ImageViewHolder(parent);
        holder.setOnImgClickListener(onImgClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(dataList.get(position),position);
    }

    public void setOnImgClickListener(ImageViewHolder.OnImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
    }
}
