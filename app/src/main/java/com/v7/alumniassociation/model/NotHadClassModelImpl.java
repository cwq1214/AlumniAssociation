package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.ClassListItemBean;
import com.v7.alumniassociation.contract.NotHadClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/8.
 */

public class NotHadClassModelImpl implements NotHadClassContract.NotHadClassModel {
    Context context;
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void loadClassList(final BaseCallback callback) {
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.classList).build().execute(new BeanCallback<BaseJson<List<ClassListItemBean>>>(context) {
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
}
