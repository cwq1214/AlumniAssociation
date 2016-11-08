package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.PersonalContract;
import com.v7.alumniassociation.model.PersonalModelImpl;
import com.v7.alumniassociation.sp.UserInfo;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalPresenterImpl implements PersonalContract.PersonalPresenter{
    PersonalContract.PersonalModel model;
    PersonalContract.PersonalView view;


    public PersonalPresenterImpl() {
        model = new PersonalModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (PersonalContract.PersonalView) view;
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
    public void getPersonalCenterViewData() {
        model.getPersonalCenterData(UserInfo.getUserId(), new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onDataCallback(isSuccess, (User) extra);
            }
        });
    }
}
