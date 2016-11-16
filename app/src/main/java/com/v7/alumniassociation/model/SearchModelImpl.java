package com.v7.alumniassociation.model;

import android.content.Context;
import android.text.TextUtils;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.ClassListItemBean;
import com.v7.alumniassociation.contract.SearchClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/13.
 */

public class SearchModelImpl implements SearchClassContract.SearchModel {
    Context context;
    @Override
    public void searchClass(String searchKey, final BaseCallback callback) {
        String par = TextUtils.isEmpty(searchKey)?"":("?keyWord="+searchKey);
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.classList+par).build().execute(new BeanCallback<BaseJson<List<ClassListItemBean>>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<List<ClassListItemBean>> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
