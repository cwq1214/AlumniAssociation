package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.Class;

/**
 * Created by v7 on 2016/11/8.
 */

public class JoinClassContract {
    public interface JoinClassModel extends BaseContract.BaseModel {
        void getClassInfo(int classId, BaseCallback callback);
        void applyJoinClass(int userId,int classId,String reason,BaseCallback callback);
    }

    public interface JoinClassView extends BaseContract.BaseView {
        void onClassInfoCallback(boolean isSuccess, Class classBean);
        void onApplyJoinClassCallback(boolean isSuccess);
    }

    public interface JoinClassPresenter extends BaseContract.BasePresenter {
        void getClassInfo(int classId);
        void applyJoinClass(int classId,String reason);
    }
}
