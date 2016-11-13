package com.v7.alumniassociation.model;

import android.content.Context;
import android.util.Base64;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.UploadImgBean;
import com.v7.alumniassociation.contract.SendPostContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/12.
 */

public class SendPostModelImpl implements SendPostContract.SendPostModel {
    Context context;

    @Override
    public void uploadImage(List<String> images, BaseCallback callback) {
        try {
            if (images!=null)
            for (int i=0;i<images.size();i++){
                final File file = new File(images.get(i));
                FileInputStream inputFile = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputFile.read(buffer);
                inputFile.close();

//                UploadImgBean bean = UploadImgBean.getUploadBean(id, Base64.encodeToString(buffer,Base64.DEFAULT),0);
//
//                OkHttpUtils.postString().url().build().execute(new BeanCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Object response, int id) {
//
//                    }
//                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void uploadPost(List<String> images, int userId, String content, final BaseCallback callback) {
        String par = "?userId"+userId+"&content="+content;
        if (images!=null)
        for (int i=0;i<images.size();i++){
            par+="&img="+images.get(i);
        }
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.sendPost+par).build().execute(new BeanCallback<BaseJson>(context) {
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
