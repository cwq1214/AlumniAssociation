package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.contract.BBSPostDetailContract;
import com.v7.alumniassociation.contract.BaseContract;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailPresenterImpl implements BBSPostDetailContract.BBSPostDetailPresenter {
    BBSPostDetailContract.BBSPostDetailModel model;
    BBSPostDetailContract.BBSPostDetailView view;

    @Override
    public void attachView(BaseContract.BaseView view) {

    }

    @Override
    public void setContext(Context context) {

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

    }

    @Override
    public void sendPost(int userId, int postId, String message) {

    }
}
