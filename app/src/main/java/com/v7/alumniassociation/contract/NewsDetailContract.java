package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;

/**
 * Created by v7 on 2016/11/12.
 */

public class NewsDetailContract {
    public interface NewsDetailModel extends BaseContract.BaseModel{
        void loadNewsDetail(int newsId , BaseCallback callback);
        void collection(NewsBean bean ,boolean collection, BaseCallback callback);
        void doLike(int newId,int userId,boolean like ,BaseCallback callback);
        void isCollection(int newsId ,BaseCallback callback);
    }
    public interface NewsDetailView extends BaseContract.BaseView{
        void onLoadNewsDetail(boolean isSuccess , NewsBean newsBean);
        void onCollectionClick(boolean isSuccess ,boolean collection);
        void onDoLick(boolean isSuccess , boolean isLike);
        void onIsCollectionCallback(boolean isSuccess,boolean isCollection);
    }
    public interface NewsDetailPresenter extends BaseContract.BasePresenter {
        void loadNewsDetail(int newsId);
        void collection(NewsBean bean,boolean collection);
        void doLike(int newId,int userId,boolean like);
        void isCollection(int newsId);
    }
}
