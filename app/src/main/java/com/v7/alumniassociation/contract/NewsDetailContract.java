package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;

/**
 * Created by v7 on 2016/11/12.
 */

public class NewsDetailContract {
    public interface NewsDetailModel extends BaseContract.BaseModel{
        void loadNewsDetail(int newsId , BaseCallback callback);
        void collection(NewsBean bean , BaseCallback callback);
        void doLike(int newId,int userId,boolean like ,BaseCallback callback);
    }
    public interface NewsDetailView extends BaseContract.BaseView{
        void onLoadNewsDetail(boolean isSuccess , NewsBean newsBean);
        void onCollectionClick(boolean isSuccess ,boolean collection);
        void onDoLick(boolean isSuccess , boolean isLike);
    }
    public interface NewsDetailPresenter extends BaseContract.BasePresenter {
        void loadNewsDetail(int newsId);
        void collection(NewsBean bean);
        void doLike(int newId,int userId,boolean like);
    }
}
