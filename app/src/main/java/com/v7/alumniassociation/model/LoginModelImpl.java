package com.v7.alumniassociation.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.LoginBean;
import com.v7.alumniassociation.contract.LoginContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.v7.alumniassociation.sp.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/7.
 */

public class LoginModelImpl implements LoginContract.LoginModel{
    Context context ;

    @Override
    public void login(String username, String password, final BaseCallback callback) {
        LoginBean loginBean = new LoginBean();
        loginBean.no = username;
        loginBean.password = password;

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.login).content(new Gson().toJson(loginBean)).build().execute(new BeanCallback<BaseJson<LoginBean>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("onError",e.getMessage());
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<LoginBean> response, int id) {
                Log.e("onResponse",response.toString());
                if (response.ret){
                    System.out.println(response.ret);
                    UserInfo.setUserId(response.data.id);
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
