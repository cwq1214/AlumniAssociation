package com.v7.alumniassociation.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.NewsBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/11.
 */

public class NewsViewHolder extends BaseViewHolder<NewsBean> {
    @BindView(R.id.newsImg)
    ImageView newsImg;
    @BindView(R.id.newsTitle)
    TextView newsTitle;
    @BindView(R.id.newsDate)
    TextView newsDate;
    @BindView(R.id.likeNum)
    TextView likeNum;
    @BindView(R.id.likeImg)
    ImageView likeImg;

    OnItemClickListener onItemClickListener;

    public NewsViewHolder(View itemView) {
        super(itemView);
    }

    public NewsViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false));
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onNewsClick(data);
                }
            }
        });
    }

    @Override
    public void onBind(NewsBean data, int index) {
        super.onBind(data, index);

        Glide.with(itemView.getContext()).load(data.n_img).into(newsImg);

        newsTitle.setText(data.n_title);

        newsDate.setText(data.createdTime);

        likeNum.setText(data.likes+"");

        if (data.liked){
            likeImg.setImageResource(R.mipmap.good);
        }else {
            likeImg.setImageResource(R.mipmap.good_nor);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onNewsClick(NewsBean bean);
    }
}
