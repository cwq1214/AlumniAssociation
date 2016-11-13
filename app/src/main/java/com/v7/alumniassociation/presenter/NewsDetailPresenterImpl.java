package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.NewsDetailContract;
import com.v7.alumniassociation.model.NewsDetailModelImpl;

/**
 * Created by v7 on 2016/11/12.
 */

public class NewsDetailPresenterImpl implements NewsDetailContract.NewsDetailPresenter {

    NewsDetailContract.NewsDetailModel model;
    NewsDetailContract.NewsDetailView view;

    public NewsDetailPresenterImpl() {
        model = new NewsDetailModelImpl();
    }

    @Override
    public void loadNewsDetail(int newsId) {
        model.loadNewsDetail(newsId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onLoadNewsDetail(isSuccess, (NewsBean) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void collection(NewsBean bean) {

    }

    @Override
    public void doLike(int newId, int userId, boolean like) {

    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (NewsDetailContract.NewsDetailView) view;
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
