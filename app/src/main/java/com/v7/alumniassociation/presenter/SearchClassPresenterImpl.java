package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.ClassListItemBean;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.SearchClassContract;
import com.v7.alumniassociation.model.SearchModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/8.
 */

public class SearchClassPresenterImpl implements SearchClassContract.SearchPresenter {
    SearchClassContract.SearchView view;
    SearchClassContract.SearchModel model;

    public SearchClassPresenterImpl() {
        model = new SearchModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (SearchClassContract.SearchView) view;
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
    public void getClassList(String keyword) {
        model.searchClass(keyword, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onClassListClassBack(isSuccess, (List<ClassListItemBean>) extra);
                view.showToast(message);
            }
        });
    }
}
