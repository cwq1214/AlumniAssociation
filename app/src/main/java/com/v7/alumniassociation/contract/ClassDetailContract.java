package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.Class;

/**
 * Created by v7 on 2016/11/8.
 */

public class ClassDetailContract {
    public interface ClassDetailModel extends BaseContract.BaseModel{
        void getClassDetail(int classId, BaseCallback callback);
        void modifyClassAvatar(int classId,String imagePath, BaseCallback callback);
        void modifyClassName(int classId,String className, BaseCallback callback);
        void modifyClassDepartment(int classId,String department, BaseCallback callback);
        void modifyClassLevel(int classId,String level, BaseCallback callback);
        void modifyClassIntroduce(int classId,String introduce, BaseCallback callback);
        void changeAdministrator(int classId,int userId, BaseCallback callback);
        void removeUser(int classId,int userId, BaseCallback callback);
        void dissolveClass(int classId , BaseCallback callback);
    }
    public interface ClassDetailView extends BaseContract.BaseView{
        void onClassDetailCallback(boolean isSuccess, Class aClass);
        void onModifyClassAvatar(boolean isSuccess,String imageUrl);
        void onModifyClassName(boolean isSuccess,String className);
        void onModifyClassDepartment(boolean isSuccess,String department);
        void onModifyClassLevel(boolean isSuccess,String level);
        void onModifyClassIntroduce(boolean isSuccess,String introduce);
        void onChangeAdministrator(boolean isSuccess,int userId);
        void onRemoveUser(boolean isSuccess , int userId);
        void onDissolveClass(boolean isSuccess);
    }
    public interface ClassDetailPresenter extends BaseContract.BasePresenter{
        void getClassDetail(int classId);
        void modifyClassAvatar(int classId,String imagePath);
        void modifyClassName(int classId,String className);
        void modifyClassDepartment(int classId,String department);
        void modifyClassLevel(int classId,String level);
        void modifyClassIntroduce(int classId,String introduce);
        void changeAdministrator(int classId,int userId);
        void removeUser(int classId,int userId);
        void dissolveClass(int classId);
    }
}
