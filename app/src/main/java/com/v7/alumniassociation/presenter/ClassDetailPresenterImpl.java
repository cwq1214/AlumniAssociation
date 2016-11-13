package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.ClassDetailContract;
import com.v7.alumniassociation.model.ClassDetailModelImpl;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassDetailPresenterImpl implements ClassDetailContract.ClassDetailPresenter {

    ClassDetailContract.ClassDetailModel model;
    ClassDetailContract.ClassDetailView view;

    public ClassDetailPresenterImpl() {
        model = new ClassDetailModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (ClassDetailContract.ClassDetailView) view;
    }

    @Override
    public void setContext(Context context) {
        model.setContext(context);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getClassDetail(int classId) {
        model.getClassDetail(classId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onClassDetailCallback(isSuccess, (com.v7.alumniassociation.bean.Class) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void modifyClassAvatar(int classId, String imagePath) {
        model.modifyClassAvatar(classId, imagePath, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onModifyClassAvatar(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void modifyClassName(int classId, String className) {
        model.modifyClassName(classId, className, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onModifyClassName(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void modifyClassDepartment(int classId, String department) {
        model.modifyClassDepartment(classId, department, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onModifyClassDepartment(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void modifyClassLevel(int classId, String level) {
        model.modifyClassLevel(classId, level, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onModifyClassLevel(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void modifyClassIntroduce(int classId, String introduce) {
        model.modifyClassIntroduce(classId, introduce, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onModifyClassIntroduce(isSuccess, (String) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void changeAdministrator(int classId, int userId) {
        model.changeAdministrator(classId, userId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onChangeAdministrator(isSuccess, 0);
                view.showToast(message);
            }
        });
    }

    @Override
    public void removeUser(int classId, int userId) {
        model.removeUser(classId, userId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onRemoveUser(isSuccess,0);
                view.showToast(message);
            }
        });
    }

    @Override
    public void dissolveClass(int classId) {
        model.dissolveClass(classId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onDissolveClass(isSuccess);
                view.showToast(message);
            }
        });
    }
}
