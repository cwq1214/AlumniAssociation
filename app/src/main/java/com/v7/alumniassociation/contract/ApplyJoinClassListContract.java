package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.ApplyItemBean;

import java.util.List;

/**
 * Created by v7 on 2016/11/12.
 */

public class ApplyJoinClassListContract {
    public interface ApplyJoinClassModel extends BaseContract.BaseModel{
        void loadList(int classId , BaseCallback callback);
        void agree(int applyId,int classId,String apply ,BaseCallback callback);
    }
    public interface ApplyJoinClassView extends BaseContract.BaseView{
        void onLoadListCallback(boolean isSuccess, List<ApplyItemBean> been);
        void oiAgreeCallback(boolean isSuccess);
    }
    public interface ApplyJoinClassPresenter extends BaseContract.BasePresenter{
        void loadList(int classId);
        void agree(int applyId,int classId,String apply);
    }
}
