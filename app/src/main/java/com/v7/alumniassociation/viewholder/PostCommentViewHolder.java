package com.v7.alumniassociation.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.PostDetailBean;

import org.apmem.tools.layouts.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/13.
 */

public class PostCommentViewHolder extends BaseViewHolder<PostDetailBean.Comment> {
    @BindView(R.id.userAvatar)
    ImageView userAvatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout;

    OnItemClickListener onItemClickListener;

    public PostCommentViewHolder(View itemView) {
        super(itemView);
    }

    public PostCommentViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_head, parent, false));
        ButterKnife.bind(this, itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener!=null)
                    return onItemClickListener.onLongClick(data,index);
                return false;
            }
        });
    }

    @Override
    public void onBind(PostDetailBean.Comment data, int index) {
        super.onBind(data, index);

        flowLayout.setVisibility(View.GONE);

        Glide.with(itemView.getContext()).load(data.img).into(userAvatar);
        userName.setText(data.name);
        date.setText(data.createdTime);
        level.setText(index+1+"楼");
        content.setText(data.content);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        boolean onLongClick(PostDetailBean.Comment data,int index);
    }
}
