package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.NewsDetailContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.apache.http.params.HttpParams;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/12.
 */

public class NewsDetailModelImpl implements NewsDetailContract.NewsDetailModel {
    Context context;

    @Override
    public void loadNewsDetail(int newsId, final BaseCallback callback) {
        OkHttpUtils.get().url(HttpUrl.domain+ HttpUrl.newsDetail+newsId).build().execute(new BeanCallback<BaseJson<NewsBean>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<NewsBean> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void collection(NewsBean bean, BaseCallback callback) {

    }

    @Override
    public void doLike(int newId, int userId, boolean like, BaseCallback callback) {

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
