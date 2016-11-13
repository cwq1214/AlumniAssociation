package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;

import java.util.List;

/**
 * Created by v7 on 2016/11/12.
 */

public class SendPostContract {
    public interface SendPostModel extends BaseContract.BaseModel{
        void uploadImage(List<String> images, BaseCallback callback);
        void uploadPost(List<String> images,int userId,String content,BaseCallback callback);
    }
    public interface SendPostView extends BaseContract.BaseView{
        void uploadImage(boolean isSuccess,List<String> images);
        void uploadPost(boolean isSuccess);
    }
    public interface SendPostPresenter extends BaseContract.BasePresenter{
        void uploadImage(List<String> images);
        void uploadPost(List<String> images,int userId,String content);
    }

}
