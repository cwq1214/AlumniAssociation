package com.v7.alumniassociation.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.MsgBoardItem;
import com.v7.alumniassociation.sp.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/9.
 */

public class MsgBoardItemViewHolder extends BaseViewHolder<MsgBoardItem> {


    @BindView(R.id.userAvatar)
    ImageView userAvatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.delete)
    TextView delete;
    @BindView(R.id.content)
    TextView content;
    OnDeleteClickListener onDeleteClickListener;

    public MsgBoardItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public MsgBoardItemViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_msgboarditem, parent,false));
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void onBind(final MsgBoardItem data, final int index) {
        super.onBind(data, index);

        Glide.with(itemView.getContext()).load(data.img).into(userAvatar);
        userName.setText(data.name);
        date.setText(data.createdTime);
        content.setText(data.content);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener!=null)
                    onDeleteClickListener.onDeleteClick(data,index);
            }
        });

        if (data.userId== UserInfo.getUserId()||UserInfo.getClassId()!=null){
            delete.setVisibility(View.VISIBLE);
        }else {
            delete.setVisibility(View.GONE);
        }

    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener{
        void onDeleteClick(MsgBoardItem data,int index);
    }

}
