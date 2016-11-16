package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.PostDetailBean;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailContract {
    public interface BBSPostDetailModel extends BaseContract.BaseModel{
        void loadPost(int post, BaseCallback callback);
        void sendPost(int userId,int postId,String message,BaseCallback callback);
        void deletePost(int postId,BaseCallback callback);

    }
    public interface BBSPostDetailView extends BaseContract.BaseView {
        void onLoadPostCallback(boolean isSuccess, PostDetailBean bean);
        void onSendPostCallback(boolean isSuccess);
        void onDeletePost(boolean isSuccess);


    }
    public interface BBSPostDetailPresenter extends BaseContract.BasePresenter {
        void loadPost(int postId);
        void sendPost(int userId,int postId,String message);
        void deletePost(int postId);
    }

}
