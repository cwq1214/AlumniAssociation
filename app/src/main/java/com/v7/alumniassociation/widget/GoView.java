package com.v7.alumniassociation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.v7.alumniassociation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/7.
 */

public class GoView extends FrameLayout {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subTitle)
    TextView subTitle;
    @BindView(R.id.go)
    ImageView go;

    public GoView(Context context) {
        this(context, null);
    }

    public GoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_go_item, this, true);
        ButterKnife.bind(this);
        if (!isInEditMode())
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GoView);

        String titleText = typedArray.getString(R.styleable.GoView_gv_titleText);
        if (TextUtils.isEmpty(titleText)){
            int resId = typedArray.getResourceId(R.styleable.GoView_gv_titleText,-1);
            if (resId!=-1){
                title.setText(getResources().getString(resId));
            }
        }else {
            title.setText(titleText);
        }

        String subTitleText = typedArray.getString(R.styleable.GoView_gv_subTitleText);
        if (TextUtils.isEmpty(subTitleText)){
            int resId = typedArray.getResourceId(R.styleable.GoView_gv_subTitleText,-1);
            if (resId!=-1){
                subTitle.setText(getResources().getString(resId));
            }
        }else {
            subTitle.setText(subTitleText);
        }

        int subTitleBackgroundResId = typedArray.getResourceId(R.styleable.GoView_gv_subTitleBackground,-1);
        if (subTitleBackgroundResId!=-1){
            subTitle.setBackgroundResource(subTitleBackgroundResId);
        }

        typedArray.recycle();
    }


    public void setTitleText(String titleText){
        title.setText(titleText);
    }

    public void setSubTitleText(String titleText){
        subTitle.setText(titleText);
    }

    public void setSubTitleBackground(int resId){
        subTitle.setBackgroundResource(resId);
    }
}
