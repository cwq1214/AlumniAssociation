package com.v7.alumniassociation.adapter;

import android.view.ViewGroup;

import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;
import com.v7.alumniassociation.viewholder.BaseViewHolder;
import com.v7.alumniassociation.viewholder.MsgBoardItemViewHolder;

/**
 * Created by v7 on 2016/11/9.
 */

public class MsgBoardAdapter extends BaseRefreshRcvAdapter {
    MsgBoardItemViewHolder.OnDeleteClickListener onDeleteClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MsgBoardItemViewHolder boardItemViewHolder = new MsgBoardItemViewHolder(parent);
        if (onDeleteClickListener!=null)
            boardItemViewHolder.setOnDeleteClickListener(onDeleteClickListener);
        return boardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(dataList.get(position),position);
    }

    public void setOnDeleteClickListener(MsgBoardItemViewHolder.OnDeleteClickListener onDeleteClickListener){
        this.onDeleteClickListener = onDeleteClickListener;
    }
}
