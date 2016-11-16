package com.v7.alumniassociation.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.FengcaiBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/13.
 */

public class FeangcaiViewHolder extends BaseViewHolder<FengcaiBean> {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.levelAndDepartment)
    TextView levelAndDepartment;

    public FeangcaiViewHolder(View itemView) {
        super(itemView);
    }

    public FeangcaiViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fengcai, parent, false));
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void onBind(FengcaiBean data, int index) {
        super.onBind(data, index);
        Glide.with(itemView.getContext()).load(data.s_img).into(img);
        name.setText(data.s_name);
        levelAndDepartment.setText(data.s_series);
    }
}
