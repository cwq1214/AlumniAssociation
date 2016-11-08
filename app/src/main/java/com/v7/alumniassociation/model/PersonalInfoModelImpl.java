package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.PersonalContract;
import com.v7.alumniassociation.contract.PersonalInfoContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalInfoModelImpl implements PersonalInfoContract.PersonalInfoModel {
    Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void uploadImg(int id, String imgPath, BaseCallback callback) {

    }

    @Override
    public void updateUserInfo(int id, User user, BaseCallback callback) {

    }

    @Override
    public void getUserInfo(int id, BaseCallback callback) {
        OkHttpUtils.post().url(HttpUrl.domain+HttpUrl.userInfoDetail+id).build().execute(new BeanCallback(context) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Object response, int id) {

            }
        });
    }
}
