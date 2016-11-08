package com.v7.alumniassociation.presenter;

import android.content.Context;
import android.view.View;

import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.ClassContract;

/**
 * Created by v7 on 2016/11/6.
 */

public class ClassPresenterImpl implements ClassContract.ClassPresenter {
    ClassContract.ClassModel model;
    ClassContract.ClassView view;

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (ClassContract.ClassView) view;
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
