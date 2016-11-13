package com.v7.alumniassociation.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.v7.alumniassociation.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v7 on 2016/11/9.
 */

public abstract class BaseRefreshRcvAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected List dataList;

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public void refreshTop(List dataList){
        getDataList().clear();
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void refreshBottom(List dataList){
        getDataList().add(dataList);
        notifyDataSetChanged();
    }


    public List getDataList(){
        if (dataList==null){
            dataList = new ArrayList<>();
        }
        return dataList;
    }

    @Override
    public int getItemCount() {
        if (dataList!=null){
            return dataList.size();
        }
        return 0;
    }
}
