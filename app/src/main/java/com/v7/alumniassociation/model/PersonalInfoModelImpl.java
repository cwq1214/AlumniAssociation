package com.v7.alumniassociation.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.ModifyUserInfoBean;
import com.v7.alumniassociation.bean.UploadImgBean;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.PersonalContract;
import com.v7.alumniassociation.contract.PersonalInfoContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    public void uploadImg(int id, String imgPath, final BaseCallback callback) {
        try {
            final File file = new File(imgPath);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();

            UploadImgBean bean = UploadImgBean.getUploadBean(id,Base64.encodeToString(buffer,Base64.DEFAULT),0);

            OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.uploadImg).content(new Gson().toJson(bean)).build().execute(new BeanCallback<String>(context) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    callback.result(false,e.getMessage(),null);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (TextUtils.isEmpty(response)){
                        callback.result(false,"不知道什么错",null);
                    }else {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String imgUrl = jsonObject.getString("imgUrl");
                            callback.result(true,"上传成功",imgUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserInfo(int id, final String updateKey, final String updateValue, final BaseCallback callback) {
        ModifyUserInfoBean bean = new ModifyUserInfoBean();
        bean.id=id;
        bean.keyWord = updateKey;
        bean.value = updateValue;
        OkHttpUtils.postString()
                .url(HttpUrl.domain+HttpUrl.modifyUserInfo)
                .content(new Gson().toJson(bean))
        .build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,"修改成功",updateValue);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
        ;
    }


    @Override
    public void getUserInfo(int id, final BaseCallback callback) {
        OkHttpUtils.post().url(HttpUrl.domain+HttpUrl.userInfoDetail+id).build().execute(new BeanCallback<BaseJson<User>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<User> response, int id) {
                if (response.ret){
                    callback.result(true,response.forUser,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }
}
