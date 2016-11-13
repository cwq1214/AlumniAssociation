package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.HomeContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.v7.alumniassociation.sp.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/11.
 */

public class HomeModelImpl implements HomeContract.HomeModel {
    Context context;
    @Override
    public void refreshNewsList(int lastId, final BaseCallback callback) {

        int userId = UserInfo.getUserId()==null?0:UserInfo.getUserId();
        String par = "?userId="+userId+(lastId==-1?"":lastId);
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.newList+par).build().execute(new BeanCallback<BaseJson<List<NewsBean>>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<List<NewsBean>> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void refreshFengcar(int lastId, BaseCallback callback) {

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
