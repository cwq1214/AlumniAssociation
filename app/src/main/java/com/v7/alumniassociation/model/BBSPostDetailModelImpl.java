package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.PostDetailBean;
import com.v7.alumniassociation.contract.BBSPostDetailContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailModelImpl  implements BBSPostDetailContract.BBSPostDetailModel{
    Context context ;
    @Override
    public void loadPost(int post, final BaseCallback callback) {
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.bbsDetail+post).build().execute(new BeanCallback<BaseJson<PostDetailBean>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<PostDetailBean> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void sendPost(int userId, int postId, String message, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("barId",postId);
            jsonObject.put("content",message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.commentPost).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,"回帖成功",null);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void deletePost(int postId, final BaseCallback callback) {
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.deleteComment+postId).build().execute(new BeanCallback<BaseJson>(context) {
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
