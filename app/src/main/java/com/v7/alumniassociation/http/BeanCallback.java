package com.v7.alumniassociation.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.v7.alumniassociation.base.AlmAsoApp;
import com.v7.alumniassociation.dialog.LoadingDialog;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by V7 on 2016/11/5.
 */

public abstract class BeanCallback<T> extends Callback<T> {

    LoadingDialog dialog ;

    Context context;

    public BeanCallback(Context context) {
        this.context = context;
        dialog = new LoadingDialog(context);
    }
    public BeanCallback(Context context,boolean cancelable) {
        this.context = context;
        dialog = new LoadingDialog(context);
        dialog.setCancelable(cancelable);
    }
    public BeanCallback(Context context,String  message) {
        this.context = context;
        dialog = new LoadingDialog(context);
        dialog.setText(message);
    }
    public BeanCallback(Context context,boolean cancelable,String message) {
        this.context = context;
        dialog = new LoadingDialog(context);
        dialog.setCancelable(cancelable);
        dialog.setText(message);
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (!dialog.isShowing())
            dialog.show();

    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        if (dialog.isShowing())
            dialog.dismiss();
    }
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        Type type = this.getClass().getGenericSuperclass();
        String bodyString = response.body().string() ;
        try {
            Log.e("http",bodyString);

        if (type instanceof ParameterizedType) {

            //如果用户写了泛型，就会进入这里，否者不会执行
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type beanType = parameterizedType.getActualTypeArguments()[0];
            if (beanType == String.class) {
                //如果是String类型，直接返回字符串
                return (T) bodyString;
            } else {
                //如果是 Bean List Map ，则解析完后返回
                return new Gson().fromJson(bodyString, beanType);
            }
        } else {
            //默认返回字符串
            return (T) bodyString;
        }
        }catch (RuntimeException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T) bodyString;
    }


}