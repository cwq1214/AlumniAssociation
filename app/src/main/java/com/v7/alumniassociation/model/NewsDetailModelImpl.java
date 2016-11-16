package com.v7.alumniassociation.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.NewsDetailContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.v7.alumniassociation.sp.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;

import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/12.
 */

public class NewsDetailModelImpl implements NewsDetailContract.NewsDetailModel {
    Context context;


    @Override
    public void loadNewsDetail(int newsId, final BaseCallback callback) {
        String par = "?newsId="+newsId+"&userId="+(UserInfo.getUserId()==null?"0":UserInfo.getUserId());
        OkHttpUtils.get().url(HttpUrl.domain+ HttpUrl.newsDetail+par).build().execute(new BeanCallback<BaseJson<NewsBean>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<NewsBean> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void collection(NewsBean bean,boolean collection, BaseCallback callback) {
        Log.e("filepath",context.getExternalCacheDir().getAbsolutePath()+"/news/"+ UserInfo.getUserId()+"/"+bean.newsId+".json");
        File file = new File(context.getExternalCacheDir().getAbsolutePath()+"/news/"+ UserInfo.getUserId()+"/"+bean.newsId+".json");
        try {
            if (collection) {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                BufferedWriter writer =new BufferedWriter(new FileWriter(file));
                writer.write(new Gson().toJson(bean));
                writer.close();
                callback.result(true, "收藏成功", true);
                return;
            }else {
                if (file.exists()){
                    file.delete();
                }
                callback.result(true,"取消收藏",false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.result(false,"",false);
        }
    }

    @Override
    public void doLike(int newId, int userId, final boolean like, final BaseCallback callback) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("newsId",newId);
            jsonObject.put("userId",userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.doLike).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),like);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,null,!like);
                }else {
                    callback.result(false,response.forUser,like);
                }
            }
        });
    }

    @Override
    public void isCollection(int newsId, BaseCallback callback) {
        File file = new File(context.getExternalCacheDir().getAbsolutePath()+"/news/"+ UserInfo.getUserId()+"/"+newsId+".json");

        callback.result(true,null,file.exists());
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
