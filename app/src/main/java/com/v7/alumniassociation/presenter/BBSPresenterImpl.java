package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BBSPostItemBean;
import com.v7.alumniassociation.contract.BBSContract;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.model.BBSModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/7.
 */

public class BBSPresenterImpl implements BBSContract.BBSPresenter {
    BBSContract.BBSModel model;
    BBSContract.BBSView view;

    public BBSPresenterImpl() {
        model = new BBSModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (BBSContract.BBSView) view;
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
    public void refreshBBSList(final int lastId) {
        model.refreshBBS(lastId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onFreshBBS(isSuccess, (List<BBSPostItemBean>) extra,lastId==-1);
                view.showToast(message);
            }
        });
    }

    @Override
    public void deletePost(final int postId) {
        model.deletePost(postId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onDeleteCallback(isSuccess,postId);
                view.showToast(message);
            }
        });
    }
}
