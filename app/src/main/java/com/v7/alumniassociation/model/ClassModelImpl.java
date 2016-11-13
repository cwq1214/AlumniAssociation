package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BaseJson;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.contract.ClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.http.HttpUrl;
import com.v7.alumniassociation.sp.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassModelImpl implements ClassContract.ClassModel {
    Context context;

    @Override
    public void hadClass(int userId, final BaseCallback callback) {
        OkHttpUtils.post().url(HttpUrl.domain+HttpUrl.classInfo+userId).build().execute(new BeanCallback<BaseJson<Class>>(context) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.result(false,e.getMessage(),null);
                UserInfo.setClassId(null);

            }

            @Override
            public void onResponse(BaseJson<Class> response, int id) {
                if (response.ret){
                    System.out.println();
                    if (response.data.classAdminId== UserInfo.getUserId()){
                        UserInfo.setClassId(response.data.classId);
                    }else {
                        UserInfo.setClassId(null);
                    }
                    callback.result(true,null,response.data);
                }else {
                    UserInfo.setClassId(null);

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
