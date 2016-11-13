package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.ClassContract;
import com.v7.alumniassociation.model.ClassModelImpl;
import com.v7.alumniassociation.sp.UserInfo;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassPresenterImpl implements ClassContract.ClassPresenter {

    ClassContract.ClassView view;
    ClassContract.ClassModel model;

    public ClassPresenterImpl() {
        model = new ClassModelImpl();
    }

    @Override
    public void hadClass() {
        if (UserInfo.getUserId()==null){
            return;
        }
        model.hadClass(UserInfo.getUserId(), new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onHadClassCallback(isSuccess, (com.v7.alumniassociation.bean.Class) extra);
            }
        });
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (ClassContract.ClassView) view;
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
}
