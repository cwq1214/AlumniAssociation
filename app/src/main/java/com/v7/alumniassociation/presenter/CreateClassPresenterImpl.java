package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.CreateClassBean;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.CreateClassContract;
import com.v7.alumniassociation.http.BeanCallback;
import com.v7.alumniassociation.model.CreateClassModelImpl;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by v7 on 2016/11/13.
 */

public class CreateClassPresenterImpl implements CreateClassContract.CreateClassPresenter {
    CreateClassContract.CreateClassModel model;
    CreateClassContract.CreateClassView view;

    public CreateClassPresenterImpl() {
        model = new CreateClassModelImpl();
    }

    @Override
    public void submitApplyToCreateClass(CreateClassBean bean) {
        model.createClassApply(0, bean, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onCreateClassCallback(isSuccess);
                view.showToast(message);
            }
        });
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (CreateClassContract.CreateClassView) view;
    }

    @Override
    public void setContext(Context context) {
        model.setContext(context);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
