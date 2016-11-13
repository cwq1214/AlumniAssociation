package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.SendClassMessageContract;
import com.v7.alumniassociation.model.SendClassMessageModelImpl;
import com.v7.alumniassociation.sp.UserInfo;

/**
 * Created by v7 on 2016/11/9.
 */

public class SendClassMessagePresenterImpl implements SendClassMessageContract.SendClassMessagePresenter {
    SendClassMessageContract.SendClassMessageModel model;
    SendClassMessageContract.SendClassMessageView view;



    public SendClassMessagePresenterImpl() {
        model = new SendClassMessageModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (SendClassMessageContract.SendClassMessageView) view;
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
    public void sendClassMessage(String message, int classId) {
        model.sendClassMessage(message, classId, UserInfo.getUserId(), new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onSendClassMessageCallback(isSuccess);
                view.showToast(message);
            }
        });
    }
}
