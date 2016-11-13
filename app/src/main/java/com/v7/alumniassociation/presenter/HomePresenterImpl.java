package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.HomeContract;
import com.v7.alumniassociation.model.HomeModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/6.
 */

public class HomePresenterImpl implements HomeContract.HomePresenter {
    HomeContract.HomeModel model;
    HomeContract.HomeView view;

    public HomePresenterImpl() {
        model = new HomeModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (HomeContract.HomeView) view;
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
    public void refreshNewsList(final int lasdId) {
        model.refreshNewsList(lasdId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onRefreshNewsCallback(isSuccess, (List<NewsBean>) extra,lasdId==-1);
                view.showToast(message);
            }
        });
    }

    @Override
    public void refreshFengCar(final int fengcai) {
        model.refreshFengcar(fengcai, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onRefreshFengCaiCallback(isSuccess, (List) extra,fengcai==-1);
                view.showToast(message);
            }
        });
    }
}
