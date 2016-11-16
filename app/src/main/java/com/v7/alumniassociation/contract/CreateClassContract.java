package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.CreateClassBean;

/**
 * Created by v7 on 2016/11/8.
 */

public class CreateClassContract {
    public interface CreateClassModel extends BaseContract.BaseModel{
        void createClassApply(int id, CreateClassBean bean, BaseCallback callback);
    }
    public interface CreateClassView extends BaseContract.BaseView{
        void onCreateClassCallback(boolean isSuccess);
    }
    public interface CreateClassPresenter extends BaseContract.BasePresenter{
        void submitApplyToCreateClass(CreateClassBean bean);
    }
}
