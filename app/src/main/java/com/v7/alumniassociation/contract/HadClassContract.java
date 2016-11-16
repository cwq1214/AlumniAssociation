package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.MsgBoardItem;
import com.v7.alumniassociation.bean.PhotoAlbumItem;

import java.util.List;

/**
 * Created by v7 on 2016/11/6.
 */

public class HadClassContract {
    public interface HadClassModel extends BaseContract.BaseModel{
        void refreshMsgBoard(int classId,int lastId,BaseCallback callback);
        void deleteMsgBoardById(int msgId,BaseCallback callback);
        void refreshPhotoAlbum(int classId,int lastId,BaseCallback callback);

    }
    public interface HadClassView extends BaseContract.BaseView{
        void onRefreshMsgBoardCallback(boolean isSuccess, List<MsgBoardItem> msgBoardItems,boolean isRefreshTop);
        void onDeleteMsgBoardById(boolean isSuccess);
        void refreshPhotoAlbum(boolean isSuccess , List<PhotoAlbumItem> photoAlbumItems , boolean isRefreshTop);

    }
    public interface HadClassPresenter extends BaseContract.BasePresenter{
        void refreshMsgBoard(int classId,int lastId);
        void deleteMsgBoardById(int msgId);
        void refreshPhotoAlbum(int classId,int lastId);
    }
}
