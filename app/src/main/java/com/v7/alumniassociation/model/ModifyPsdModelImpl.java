package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.ModifyPsdContract;

/**
 * Created by v7 on 2016/11/7.
 */

public class ModifyPsdModelImpl implements ModifyPsdContract.ModifyPsdModel {
    Context context;
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void updatePassword(String id, String oldPsd, String newPsd, BaseCallback callback) {

    }
}
