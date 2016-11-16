package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.MsgBoardItem;
import com.v7.alumniassociation.bean.PhotoAlbumItem;
import com.v7.alumniassociation.contract.HadClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/8.
 */

public class HadClassModeImpl implements HadClassContract.HadClassModel{

    Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void refreshMsgBoard(int classId, int lastId, final BaseCallback callback) {

        String getPar = "?classId="+classId+(lastId==-1?"":"&lastId="+lastId);

        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.getMsgBoardMessage+getPar).build().execute(new BeanCallback<BaseJson<List<MsgBoardItem>>> (context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<List<MsgBoardItem>> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void deleteMsgBoardById(final int msgId, final BaseCallback callback) {
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.deleteMsgBoardMessage+msgId).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,"删除成功",msgId);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void refreshPhotoAlbum(int classId, int lastId, final BaseCallback callback) {
        String par = "?classId="+classId+(lastId==-1?"":lastId);
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.getClassPhoto+par).build().execute(new BeanCallback<BaseJson<List<PhotoAlbumItem>>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<List<PhotoAlbumItem>> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else{
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }


}
