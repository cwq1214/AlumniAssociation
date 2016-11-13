package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.SendPostContract;
import com.v7.alumniassociation.model.SendPostModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/12.
 */

public class SendPostPresenterImpl implements SendPostContract.SendPostPresenter {
    SendPostContract.SendPostModel model;
    SendPostContract.SendPostView view;

    public SendPostPresenterImpl() {
        model = new SendPostModelImpl();
    }

    @Override
    public void uploadImage(List<String> images) {
        model.uploadImage(images, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.uploadImage(isSuccess, (List<String>) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void uploadPost(List<String> images, int userId, String content) {
        model.uploadPost(images, userId, content, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.uploadPost(isSuccess);
                view.showToast(message);
            }
        });
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (SendPostContract.SendPostView) view;
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
