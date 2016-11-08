package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.HomeContract;

/**
 * Created by v7 on 2016/11/6.
 */

public class HomePresenterImpl implements HomeContract.HomePresenter {
    HomeContract.HomeModel model;
    HomeContract.HomeView view;

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (HomeContract.HomeView) view;
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
}
