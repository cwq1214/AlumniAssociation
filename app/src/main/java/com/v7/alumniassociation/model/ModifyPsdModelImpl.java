package com.v7.alumniassociation.model;

import android.content.Context;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.UpLoadPsdBean;
import com.v7.alumniassociation.contract.ModifyPsdContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/7.
 */

public class ModifyPsdModelImpl implements ModifyPsdContract.ModifyPsdModel {
    Context context;
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void updatePassword(int id, String oldPsd, String newPsd, final BaseCallback callback) {


        UpLoadPsdBean bean = new UpLoadPsdBean();
        bean.id=id;
        bean.password = newPsd;
        bean.oldpassword = oldPsd;
        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.updatePsd).content(new Gson().toJson(bean)).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,"修改成功",null);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }
}
