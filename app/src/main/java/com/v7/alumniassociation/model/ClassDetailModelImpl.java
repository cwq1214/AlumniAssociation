package com.v7.alumniassociation.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.UploadImgBean;
import com.v7.alumniassociation.contract.ClassDetailContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassDetailModelImpl implements ClassDetailContract.ClassDetailModel {

    Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void getClassDetail(int classId, final BaseCallback callback) {
        String par = "?classId="+classId+"&user=1";
        OkHttpUtils.get().url(HttpUrl.domain+HttpUrl.getClassDetail+par).build().execute(new BeanCallback<BaseJson<Class>>(context) {

            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson<Class> response, int id) {
                if (response.ret){
                    callback.result(true,null,response.data);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void modifyClassAvatar(int classId, String imagePath, final BaseCallback callback) {
        try {
        final File file = new File(imagePath);
        FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            UploadImgBean bean = UploadImgBean.getUploadBean(classId, Base64.encodeToString(buffer,Base64.DEFAULT),1);
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
    public void modifyClassName(final int classId, final String className, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("key","name");
            jsonObject.put("value",className);
            jsonObject.put("classId",classId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.modifyClassInfo).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,null,className);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void modifyClassDepartment(int classId, final String department, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("key","series");
            jsonObject.put("value",department);
            jsonObject.put("classId",classId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.modifyClassInfo).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,null,department);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void modifyClassLevel(int classId, final String level, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("key","level");
            jsonObject.put("value",level);
            jsonObject.put("classId",classId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.modifyClassInfo).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,null,level);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void modifyClassIntroduce(int classId, final String introduce, final BaseCallback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key","introduction");
            jsonObject.put("value",introduce);
            jsonObject.put("classId",classId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.modifyClassInfo).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.ret){
                    callback.result(true,null,introduce);
                }else {
                    callback.result(false,response.forUser,null);
                }
            }
        });
    }

    @Override
    public void changeAdministrator(int classId, int userId, final BaseCallback callback) {
        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.setAdmin).build().execute(new BeanCallback<BaseJson>(context) {
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
    public void removeUser(int classId, int userId, final BaseCallback callback) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("userId",userId);
            jsonObject.put("classId",classId);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.removePeople).content(jsonObject.toString()).build().execute(new BeanCallback<BaseJson>(context) {
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
    public void dissolveClass(int classId, final BaseCallback callback) {

        OkHttpUtils.postString().url(HttpUrl.domain+HttpUrl.dissolve+classId).build().execute(new BeanCallback<BaseJson>(context) {
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
}
