package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BBSPostItemBean;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.contract.BBSContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSModelImpl implements BBSContract.BBSModel{
    Context context;

    @Override
    public void refreshBBS(int lastId, final BaseCallback callback) {

        String par = lastId==-1?"":"?lastId="+lastId;
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.bbsList+par).build().execute(new BeanCallback<BaseJson<List<BBSPostItemBean>>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<List<BBSPostItemBean>> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }

            }
        });
    }

    @Override
    public void deletePost(int postId, final BaseCallback callback) {
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.deleteBBSPostById+postId).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,"删除成功",null);
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
