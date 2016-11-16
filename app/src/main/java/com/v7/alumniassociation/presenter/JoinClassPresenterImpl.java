package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.JoinClassContract;
import com.v7.alumniassociation.model.JoinClassModelImpl;
import com.v7.alumniassociation.sp.UserInfo;

/**
 * Created by v7 on 2016/11/8.
 */

public class JoinClassPresenterImpl implements JoinClassContract.JoinClassPresenter {
    JoinClassContract.JoinClassView view;
    JoinClassContract.JoinClassModel model;

    public JoinClassPresenterImpl() {
        model = new JoinClassModelImpl();
    }

    @Override
    public void getClassInfo(int classId) {
        model.getClassInfo(classId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onClassInfoCallback(isSuccess, (com.v7.alumniassociation.bean.Class) extra);
            }
        });
    }

    @Override
    public void applyJoinClass(int classId, String reason) {
        model.applyJoinClass(UserInfo.getUserId(), classId, reason, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onApplyJoinClassCallback(isSuccess);
                view.showToast(message);
            }
        });
    }


    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (JoinClassContract.JoinClassView) view;
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
