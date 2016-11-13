package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.ApplyItemBean;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.contract.ApplyJoinClassListContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/13.
 */

public class ApplyJoinClassListModelImpl implements ApplyJoinClassListContract.ApplyJoinClassModel{
    Context context;

    @Override
    public void loadList(int classId, final BaseCallback callback) {
        String par = "?classId="+classId;
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.applyList+par).build().execute(new BeanCallback<BaseJson<List<ApplyItemBean>>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<List<ApplyItemBean>> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void agree(int applyId, int classId, String apply, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apply",apply);
            jsonObject.put("applyId",applyId);
            jsonObject.put("classId",classId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.doApply).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,null,null);
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
