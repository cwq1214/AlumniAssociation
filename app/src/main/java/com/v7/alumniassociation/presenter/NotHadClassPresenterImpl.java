package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.ClassListItemBean;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.NotHadClassContract;
import com.v7.alumniassociation.model.NotHadClassModelImpl;

import java.util.List;

/**
 * Created by v7 on 2016/11/8.
 */

public class NotHadClassPresenterImpl implements NotHadClassContract.NotHadClassPresenter {
    NotHadClassContract.NotHadClassModel model;
    NotHadClassContract.NotHadClassView view;

    public NotHadClassPresenterImpl() {
        model = new NotHadClassModelImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (NotHadClassContract.NotHadClassView) view;
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
    public void loadClassList() {
        model.loadClassList(new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onLoadClassListCallback(isSuccess, (List<ClassListItemBean>) extra,true);
                view.showToast(message);
            }
        });
    }
}
