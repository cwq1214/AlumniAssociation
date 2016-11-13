package com.v7.alumniassociation.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by v7 on 2016/11/9.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    protected int index;
    protected T data;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(T data ,int index){
        this.data = data;
        this.index = index;
    }

    public T getData(){
        return data;
    }



}
