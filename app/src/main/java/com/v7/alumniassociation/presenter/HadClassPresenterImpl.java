package com.v7.alumniassociation.presenter;

import android.content.Context;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.MsgBoardItem;
import com.v7.alumniassociation.bean.PhotoAlbumItem;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.HadClassContract;
import com.v7.alumniassociation.model.HadClassModeImpl;
import com.v7.alumniassociation.sp.UserInfo;

import java.util.List;

/**
 * Created by v7 on 2016/11/6.
 */

public class HadClassPresenterImpl implements HadClassContract.HadClassPresenter {
    HadClassContract.HadClassModel model;
    HadClassContract.HadClassView view;

    public HadClassPresenterImpl() {
        model = new HadClassModeImpl();
    }

    @Override
    public void attachView(BaseContract.BaseView view) {
        this.view = (HadClassContract.HadClassView) view;
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
    public void refreshMsgBoard(int classId, final int lastId) {
        model.refreshMsgBoard(classId, lastId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onRefreshMsgBoardCallback(isSuccess, (List<MsgBoardItem>) extra,lastId==-1);
            }
        });
    }

    @Override
    public void deleteMsgBoardById(int msgId) {
        model.deleteMsgBoardById(msgId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.onDeleteMsgBoardById(isSuccess);
                view.showToast(message);
            }
        });
    }

    @Override
    public void refreshPhotoAlbum(int classId, final int lastId) {
        model.refreshPhotoAlbum(classId, lastId, new BaseCallback() {
            @Override
            public void result(boolean isSuccess, String message, Object extra) {
                view.refreshPhotoAlbum(isSuccess, (List<PhotoAlbumItem>) extra,lastId==-1);
            }
        });
    }
}
