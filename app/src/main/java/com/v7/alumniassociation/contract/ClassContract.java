package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.Class;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassContract {
    public interface ClassModel extends BaseContract.BaseModel{
        void hadClass(int userId, BaseCallback callback);
    }
    public interface ClassView extends BaseContract.BaseView{
        void onHadClassCallback(boolean isSuccess, Class aClass);
    }
    public interface ClassPresenter extends BaseContract.BasePresenter{
        void hadClass();
    }
}
