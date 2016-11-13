package com.v7.alumniassociation.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.ApplyItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/12.
 */

public class ApplyItemViewHolder extends BaseViewHolder<ApplyItemBean> {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.agree)
    TextView agree;

    OnAgreeClickListener onAgreeClickListener;

    public ApplyItemViewHolder(View itemView) {
        super(itemView);
    }

    public ApplyItemViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_apply_item, parent, false));
        ButterKnife.bind(this, itemView);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAgreeClickListener!=null){
                    if (data.uc_state.equals("0"))
                        onAgreeClickListener.onAgreeClick(data,index);
                }
            }
        });

    }

    @Override
    public void onBind(ApplyItemBean data, int index) {
        super.onBind(data, index);
        date.setText(data.createdTime);
        reason.setText(data.reason);
        userName.setText(data.name);
        if (data.uc_state.equals("0")){
            agree.setEnabled(true);
            agree.setText("同意");
        }else {
            agree.setEnabled(false);
            agree.setText("已加入");

        }

    }

    public void setOnAgreeClickListener(OnAgreeClickListener onAgreeClickListener) {
        this.onAgreeClickListener = onAgreeClickListener;
    }

    public interface OnAgreeClickListener{
        void onAgreeClick(ApplyItemBean bean,int index);
    }
}
