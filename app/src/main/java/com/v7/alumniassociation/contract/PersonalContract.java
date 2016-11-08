package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.User;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalContract {
    public interface PersonalModel extends BaseContract.BaseModel{
        void getPersonalCenterData(Integer id ,BaseCallback callback);
    }
    public interface PersonalView extends BaseContract.BaseView{
        void onDataCallback(boolean isSuccess, User user);
    }
    public interface PersonalPresenter extends BaseContract.BasePresenter{
        void getPersonalCenterViewData();
    }
}
