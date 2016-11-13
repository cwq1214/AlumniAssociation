package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.BBSPostItemBean;

import java.util.List;

/**
 * Created by v7 on 2016/11/7.
 */

public class BBSContract  {
    public interface BBSModel extends BaseContract.BaseModel{
        void refreshBBS(int lastId, BaseCallback callback);
        void deletePost(int postId ,BaseCallback callback);
    }
    public interface BBSView extends BaseContract.BaseView{
        void onFreshBBS(boolean isSuccess, List<BBSPostItemBean> list,boolean isRefreshTop);
        void onDeleteCallback(boolean isSuccess ,int deleteId);
    }
    public interface BBSPresenter extends BaseContract.BasePresenter{
        void refreshBBSList(int lastId);
        void deletePost(int postId);
    }
}
