package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;

import java.util.List;

/**
 * Created by V7 on 2016/11/5.
 */

public class HomeContract {

    public interface  HomeModel extends BaseContract.BaseModel {
        void refreshNewsList(int lastId, BaseCallback callback);
        void refreshFengcar(int lastId,BaseCallback callback);
    }
    public interface HomeView extends BaseContract.BaseView {
        void onRefreshNewsCallback(boolean isSuccess, List<NewsBean> newsBeen,boolean isRefreshTop);
        void onRefreshFengCaiCallback(boolean isSuccess, List newsBeen,boolean isRefreshTop);
    }
    public interface HomePresenter extends BaseContract.BasePresenter {
        void refreshNewsList(int lasdId);
        void refreshFengCar(int fengcai);
    }
}
