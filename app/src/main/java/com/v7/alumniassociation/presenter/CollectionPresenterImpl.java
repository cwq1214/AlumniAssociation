package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.CollectionListContract;
import com.v7.alumniassociation.model.CollectionListModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/13.
 */

public class CollectionPresenterImpl implements CollectionListContract.CollectionListPresenter {
    CollectionListContract.CollectionListModel model;
    CollectionListContract.CollectionListView view;

    public CollectionPresenterImpl() {
        model = new CollectionListModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (CollectionListContract.CollectionListView) view;
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
    public void loadCollectionList(int userId) {
        model.loadCollectionList(userId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onLoadCollectionListCallback(isSuccess, (List<NewsBean>) extra);
                view.showToast(message);
            }
        });
    }
}
