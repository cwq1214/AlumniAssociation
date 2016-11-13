package com.v7.alumniassociation.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.ClassListItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassItemViewHolder extends BaseViewHolder<ClassListItemBean> {
    @BindView(R.id.classAvatar)
    ImageView classAvatar;
    @BindView(R.id.className)
    TextView className;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.classPeopleNum)
    TextView classPeopleNum;


    OnItemClickListener onItemClickListener;
    public ClassItemViewHolder(View itemView) {
        super(itemView);
    }

    public ClassItemViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_class_item, parent,false));
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(data,index);
            }
        });
    }

    @Override
    public void onBind(ClassListItemBean data, int index) {
        super.onBind(data, index);
        Glide.with(itemView.getContext()).load(data.c_img).into(classAvatar);
        className.setText(data.c_name);
        department.setText(data.c_series);
        classPeopleNum.setText(data.classCount+"");

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(ClassListItemBean aClass,int position);
    }
}
