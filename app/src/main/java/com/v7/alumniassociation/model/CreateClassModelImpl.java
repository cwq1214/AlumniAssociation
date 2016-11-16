package com.v7.alumniassociation.model;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.CreateClassBean;
import com.v7.alumniassociation.contract.CreateClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.FileInputStream;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/13.
 */

public class CreateClassModelImpl implements CreateClassContract.CreateClassModel {
    Context context ;
    @Override
    public void createClassApply(int id, CreateClassBean bean, final BaseCallback callback) {
        try {
            final File file = new File(bean.img);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();

            bean.img = Base64.encodeToString(buffer,Base64.DEFAULT);
            OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.createClass).content(new Gson().toJson(bean)).build().execute(new BeanCallback<BaseJson>(context) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    callback.result(false,e.getMessage(),null);
                }

                @Override
                public void onResponse(BaseJson response, int id) {
                    if (response.ret){
                        callback.result(true,null,response.data);
                    }else {
                        callback.result(false,response.forUser,null);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
