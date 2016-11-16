package com.v7.alumniassociation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.v7.alumniassociation.bean.PostDetailBean;
import com.v7.alumniassociation.bean.ViewType;
import com.v7.alumniassociation.viewholder.BaseViewHolder;
import com.v7.alumniassociation.viewholder.PostCommentViewHolder;
import com.v7.alumniassociation.viewholder.PostHeadViewHolder;

import java.util.List;

/**
 * Created by v7 on 2016/11/13.
 */

public class BBSDetailAdapter extends RecyclerView.Adapter {
    List<ViewType> data;
    PostCommentViewHolder.OnItemClickListener onItemClickListener;

    public void setData(List<ViewType> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ViewType.POST_HEAD:
                PostHeadViewHolder headViewHolder = new PostHeadViewHolder(parent);

                return headViewHolder;

            case ViewType.POST_COMMENT:
                PostCommentViewHolder commentViewHolder = new PostCommentViewHolder(parent);
                commentViewHolder.setOnItemClickListener(onItemClickListener);
                return commentViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).onBind(data.get(position).data,position);
    }

    @Override
    public int getItemCount() {
        if (data!=null){
            return data.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }


    public void setOnItemClickListener(PostCommentViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

