package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;

import java.util.List;

/**
 * Created by v7 on 2016/11/13.
 */

public class CollectionListContract  {
    public interface CollectionListModel extends BaseContract.BaseModel{
        void loadCollectionList(int userId, BaseCallback callback);
    }
    public interface CollectionListView extends BaseContract.BaseView{
        void onLoadCollectionListCallback(boolean isSuccess, List<NewsBean> newsBeen);
    }
    public interface CollectionListPresenter extends BaseContract.BasePresenter{
        void loadCollectionList(int userId);
    }
}
