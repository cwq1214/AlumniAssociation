package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.User;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalInfoContract {
    public interface PersonalInfoModel extends BaseContract.BaseModel{
        void uploadImg(int id, String imgPath, BaseCallback callback);
        void updateUserInfo(int id,User user,BaseCallback callback);
        void getUserInfo(int id,BaseCallback callback);
    }
    public interface PersonalInfoView extends BaseContract.BaseView{
        void uploadImgCallback(boolean isSuccess,String url);
        void updateUserInfoCallback(boolean isSuccess,String updateString);
        void onUserInfoCallback(boolean isSuccess ,User user);
    }
    public interface PersonalInfoPresenter extends BaseContract.BasePresenter {
        void uploadImg(String imgPath);
        void updateUserInfo(User user);
        void getUserInfo();
    }
}
