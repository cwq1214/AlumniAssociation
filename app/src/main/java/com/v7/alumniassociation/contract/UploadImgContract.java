package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;

import java.util.List;

/**
 * Created by v7 on 2016/11/10.
 */

public class UploadImgContract {
    public interface UploadImgModel extends BaseContract.BaseModel{
        void uploadImg(String img,int classId ,int userId, BaseCallback callback);
    }
    public interface UploadImgView extends BaseContract.BaseView{
        void uploadImgCallback(boolean isSuccess);
    }
    public interface UploadImgPresenter extends BaseContract.BasePresenter{
        void uploadImg(List<String> imgPath,int classId,int userId);
    }

}
