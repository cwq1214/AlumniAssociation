package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;

/**
 * Created by v7 on 2016/11/7.
 */

public class ModifyPsdContract {
    public interface ModifyPsdModel extends BaseContract.BaseModel{
        void updatePassword(int id, String oldPsd, String newPsd, BaseCallback callback);
    }
    public interface ModifyPsdView extends BaseContract.BaseView{
        void onUpdatePsdCallback(boolean isSuccess);
    }
    public interface ModifyPsdPresenter extends BaseContract.BasePresenter{
        void updatePassword(String oldPsd,String newPsd);
    }
}
