package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.ApplyItemBean;
import com.v7.alumniassociation.contract.ApplyJoinClassListContract;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.model.ApplyJoinClassListModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/13.
 */

public class ApplyJoinClassPresenterImpl implements ApplyJoinClassListContract.ApplyJoinClassPresenter {
    ApplyJoinClassListContract.ApplyJoinClassModel model;
    ApplyJoinClassListContract.ApplyJoinClassView view;

    public ApplyJoinClassPresenterImpl() {
        model = new ApplyJoinClassListModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (ApplyJoinClassListContract.ApplyJoinClassView) view;
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
    public void loadList(int classId) {
        model.loadList(classId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onLoadListCallback(isSuccess, (List<ApplyItemBean>) extra);
                view.showToast(message);
            }
        });
    }

    @Override
    public void agree(int applyId, int classId, String apply) {
        model.agree(applyId, classId, apply, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.oiAgreeCallback(isSuccess);
                view.showToast(message);
            }
        });
    }
}
