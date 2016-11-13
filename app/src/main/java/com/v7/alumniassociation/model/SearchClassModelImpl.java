package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.SearchClassContract;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

/**
 * Created by v7 on 2016/11/8.
 */

public class SearchClassModelImpl implements SearchClassContract.SearchModel {
    Context context ;
    @Override
    public void searchClass(String searchKey, BaseCallback callback) {


    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
