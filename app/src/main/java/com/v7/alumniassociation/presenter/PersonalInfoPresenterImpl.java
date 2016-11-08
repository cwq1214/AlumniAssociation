package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.PersonalInfoContract;
import com.v7.alumniassociation.model.PersonalInfoModelImpl;
import com.v7.alumniassociation.sp.UserInfo;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalInfoPresenterImpl implements PersonalInfoContract.PersonalInfoPresenter {
    PersonalInfoContract.PersonalInfoModel model;
    PersonalInfoContract.PersonalInfoView view;

    public PersonalInfoPresenterImpl() {
        model = new PersonalInfoModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (PersonalInfoContract.PersonalInfoView) view;
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

    @Override
    public void uploadImg(String imgPath) {
        model.uploadImg(UserInfo.getUserId(), imgPath, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.uploadImgCallback(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void updateUserInfo(User user) {
        model.updateUserInfo(UserInfo.getUserId(), user, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.updateUserInfoCallback(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void getUserInfo() {
        model.getUserInfo(UserInfo.getUserId(), new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onUserInfoCallback(isSuccess, (User) extra);
                view.showToast(message);
            }
        });
    }
}
