package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.PersonalContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.v7.alumniassociation.sp.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalModelImpl implements PersonalContract.PersonalModel {

    Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void getPersonalCenterData(Integer id, final BaseCallback callback) {
        OkHttpUtils.post().url(HttpUrl.domain+HttpUrl.userInfo+id).build().execute(new BeanCallback<BaseJson<User>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<User> response, int id) {
                if (response.ret){
                    UserInfo.setUserName(response.data.name);
                    UserInfo.setImg(response.data.img);
                    UserInfo.setNo(response.data.no);
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }
}
