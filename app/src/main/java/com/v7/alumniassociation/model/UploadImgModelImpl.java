package com.v7.alumniassociation.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.UploadImgBean;
import com.v7.alumniassociation.contract.UploadImgContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/10.
 */

public class UploadImgModelImpl implements UploadImgContract.UploadImgModel {

    Context context;
    @Override
    public void uploadImg(String img, int classId , int userId, final BaseCallback callback) {
        try {
            final File file = new File(img);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();

            final UploadImgBean bean = UploadImgBean.getUploadBean(classId,userId, Base64.encodeToString(buffer,Base64.DEFAULT));

            OkHttpUtils.postString().url(HttpUrl.domain+ HttpUrl.uploadImg).content(new Gson().toJson(bean)).build().execute(new BeanCallback<String>(context) {
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
