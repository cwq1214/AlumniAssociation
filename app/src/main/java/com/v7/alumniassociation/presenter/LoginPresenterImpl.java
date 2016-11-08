package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.LoginContract;
import com.v7.alumniassociation.model.LoginModelImpl;

/**
 * Created by v7 on 2016/11/7.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {
    LoginContract.LoginModel model;
    LoginContract.LoginView view;

    public LoginPresenterImpl() {
        model = new LoginModelImpl();
    }

    @Override
    public void login(String userName, String password) {
        model.login(userName, password, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onLoginCallback(isSuccess,extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (LoginContract.LoginView) view;
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
