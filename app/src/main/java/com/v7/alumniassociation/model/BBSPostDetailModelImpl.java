package com.v7.alumniassociation.model;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BBSPostDetailContract;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailModelImpl  implements BBSPostDetailContract.BBSPostDetailModel{
    Context context ;
    @Override
    public void loadPost(int post, BaseCallback callback) {

    }

    @Override
    public void sendPost(int userId, int postId, BaseCallback callback) {

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
