package com.v7.alumniassociation.viewholder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.PhotoAlbumItem;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.util.Screen;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/10.
 */

public class ImageViewHolder extends BaseViewHolder<PhotoAlbumItem> {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.layout)
    LinearLayout layout;

    OnImgClickListener onImgClickListener;

    public ImageViewHolder(View itemView) {
        super(itemView);
    }

    public ImageViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_img, parent,false));
        ButterKnife.bind(this, itemView);
        int itemWidth = (Screen.getWidthPixels(itemView.getContext())) / 3 - Dimension.dp2px(itemView.getContext(), 8);
        img.setLayoutParams(new FrameLayout.LayoutParams(itemWidth, itemWidth));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImgClickListener!=null)
                    onImgClickListener.onClick(data,index);
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onImgClickListener!=null){
                    onImgClickListener.onLongClick(data,index);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBind(PhotoAlbumItem data, int index) {
        super.onBind(data, index);

        Glide.with(itemView.getContext()).load(data.imgUrl).into(img);
    }


    public void setOnImgClickListener(OnImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
    }

    public interface OnImgClickListener{
        void onClick(PhotoAlbumItem item,int position);
        void onLongClick(PhotoAlbumItem item,int position);
    }
}
