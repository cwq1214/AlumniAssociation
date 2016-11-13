package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.ModifyPsdContract;
import com.v7.alumniassociation.model.ModifyPsdModelImpl;
import com.v7.alumniassociation.sp.UserInfo;

/**
 * Created by v7 on 2016/11/7.
 */

public class ModifyPsdPresenterImpl implements ModifyPsdContract.ModifyPsdPresenter {

    ModifyPsdContract.ModifyPsdModel model;
    ModifyPsdContract.ModifyPsdView view;

    public ModifyPsdPresenterImpl() {
        model = new ModifyPsdModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (ModifyPsdContract.ModifyPsdView) view;
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
    public void updatePassword(String oldPsd, String newPsd) {
        model.updatePassword(UserInfo.getUserId(), oldPsd, newPsd, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onUpdatePsdCallback(isSuccess);
            }
        });
    }
}
