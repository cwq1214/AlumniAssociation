package com.v7.alumniassociation.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.BBSPostItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostItemViewHolder extends BaseViewHolder<BBSPostItemBean> {

    OnItemClickListener onItemClickListener;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.commentLabel)
    TextView commentLabel;
    @BindView(R.id.commentCount)
    TextView commentCount;


    public BBSPostItemViewHolder(View itemView) {
        super(itemView);
    }

    public BBSPostItemViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bbs_post_item, parent, false));
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(data, index);
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemClickListener.onLongClick(data, index);
            }
        });
    }

    @Override
    public void onBind(BBSPostItemBean data, int index) {
        super.onBind(data, index);
        title.setText(data.content);
        commentCount.setText(data.barCount+"");
        date.setText(data.createdTime);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(BBSPostItemBean data, int index);

        boolean onLongClick(BBSPostItemBean data, int index);
    }
}
