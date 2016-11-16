package com.v7.alumniassociation.base;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.v7.alumniassociation.BuildConfig;
import com.v7.alumniassociation.sp.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by V7 on 2016/11/5.
 */

public class AlmAsoApp extends Application {


    static AlmAsoApp almAsoApp;

    @Override
    public void onCreate() {
        super.onCreate();

        almAsoApp = this;

        initOkHttp();
        UserInfo.init(this);

    }

    private void initOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        OkHttpUtils.initClient(builder.build());
    }

    public static AlmAsoApp getInstance(){
        return almAsoApp;
    }
}
