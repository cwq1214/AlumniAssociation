package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.UploadImgContract;
import com.v7.alumniassociation.model.UploadImgModelImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by v7 on 2016/11/10.
 */

public class UploadImgPresenterImpl implements UploadImgContract.UploadImgPresenter {
    UploadImgContract.UploadImgModel model;
    UploadImgContract.UploadImgView view;

    AtomicInteger count = new AtomicInteger(0);

    public UploadImgPresenterImpl() {
        model = new UploadImgModelImpl();
    }

    @Override
    public void uploadImg(final List<String> imgPath, int classId, int userId) {
        for (int i=0;i<imgPath.size();i++) {
            model.uploadImg(imgPath.get(i), classId, userId, new BaseCallback() {
                @Override
                public void result(boolean isSuccess, String message, Object extra) {
                    int callbackCount = count.addAndGet(1);
                    if (callbackCount == imgPath.size()-1){
                        view.uploadImgCallback(true);
                        view.showToast("上传完毕！");
                    }
                }
            });
        }
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (UploadImgContract.UploadImgView) view;
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
