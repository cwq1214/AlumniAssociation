package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.PostDetailBean;
import com.v7.alumniassociation.contract.BBSPostDetailContract;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.model.BBSPostDetailModelImpl;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailPresenterImpl implements BBSPostDetailContract.BBSPostDetailPresenter {
    BBSPostDetailContract.BBSPostDetailModel model;
    BBSPostDetailContract.BBSPostDetailView view;

    public BBSPostDetailPresenterImpl() {
        model = new BBSPostDetailModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (BBSPostDetailContract.BBSPostDetailView) view;
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
    public void loadPost(int postId) {
        model.loadPost(postId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onLoadPostCallback(isSuccess, (PostDetailBean) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void sendPost(int userId, int postId, String message) {
        model.sendPost(userId, postId,message, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onSendPostCallback(isSuccess);
                view.showToast(message);
            }
        });
    }

    @Override
    public void deletePost(int postId) {
        model.deletePost(postId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onDeletePost(isSuccess);
            }
        });
    }
}
