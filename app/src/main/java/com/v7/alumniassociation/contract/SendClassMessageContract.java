package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;

/**
 * Created by v7 on 2016/11/8.
 */

public class SendClassMessageContract {
    public interface SendClassMessageModel extends BaseContract.BaseModel{
        void sendClassMessage(String message, int classId, int userId, BaseCallback callback);
    }
    public interface SendClassMessageView extends BaseContract.BaseView{
        void onSendClassMessageCallback(boolean isSuccess);
    }
    public interface SendClassMessagePresenter extends BaseContract.BasePresenter{
        void sendClassMessage(String message,int classId);
    }
}
