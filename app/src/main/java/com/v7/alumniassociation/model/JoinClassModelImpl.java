package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.contract.JoinClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/13.
 */

public class JoinClassModelImpl implements JoinClassContract.JoinClassModel {
    Context context;
    @Override
    public void getClassInfo(int classId, final BaseCallback callback) {

        String par = "?classId="+classId+"&user=1";
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.getClassDetail+par).build().execute(new BeanCallback<BaseJson<Class>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<Class> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void applyJoinClass(int userId, int classId, final String reason, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("classId",classId);
            jsonObject.put("reason",reason);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.applyJoinClass).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
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
