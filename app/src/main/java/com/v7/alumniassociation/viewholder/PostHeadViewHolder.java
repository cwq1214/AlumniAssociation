package com.v7.alumniassociation.viewholder;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.bean.PostDetailBean;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.util.Screen;

import org.apmem.tools.layouts.FlowLayout;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/13.
 */

public class PostHeadViewHolder extends BaseViewHolder<PostDetailBean.Bar> {
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

    int width;
    int margin;
    public PostHeadViewHolder(View itemView) {
        super(itemView);
    }

    public PostHeadViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_head, parent, false));
        ButterKnife.bind(this,itemView);
        int sw = Screen.getWidthPixels(itemView.getContext())- Dimension.dp2px(itemView.getContext(),16);
        width= sw/3-Dimension.dp2px(itemView.getContext(),4);
        margin = Dimension.dp2px(itemView.getContext(),4);
    }

    @Override
    public void onBind(PostDetailBean.Bar data, int index) {
        super.onBind(data, index);
        level.setText(index+1+"楼");
        userName.setText(data.name);
        Glide.with(itemView.getContext()).load(data.img).into(userAvatar);
        date.setText(data.createdTime);
        content.setText(data.b_content);

        flowLayout.removeAllViews();
        if (!TextUtils.isEmpty(data.b_imgs)){
            Log.e("b_imgs",data.b_imgs);
            List<String> imgs = null;
            try {
                imgs = new Gson().fromJson(new JSONArray(data.b_imgs).toString(),new TypeToken<List<String>>(){}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i=0;i<imgs.size();i++){
                flowLayout.addView(buildImg(imgs.get(i)));
            }
        }

    }
    private ImageView buildImg(final String imgUrl){
        ImageView imageView = new ImageView(itemView.getContext());
        Glide.with(itemView.getContext()).load(imgUrl).into(imageView);
        imageView.setLayoutParams(getLayoutPar());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.openBrowseImageActivity(itemView.getContext(),imgUrl);
            }
        });
        return imageView;
    }

    private FlowLayout.LayoutParams getLayoutPar(){

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(width,width);
        params.setMargins(margin,margin,margin,margin);
        return params;
    }
}
