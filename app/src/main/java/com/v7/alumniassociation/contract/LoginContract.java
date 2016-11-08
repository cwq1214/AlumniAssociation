package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;

/**
 * Created by v7 on 2016/11/7.
 */

public class LoginContract {
    public interface LoginModel extends BaseContract.BaseModel{
        void login(String username, String password, BaseCallback callback);
    }
    public interface LoginView extends BaseContract.BaseView{
        void onLoginCallback(boolean isSuccess,Object bean);
    }
    public interface LoginPresenter extends BaseContract.BasePresenter{
        void login(String userName,String password);
    }
}
