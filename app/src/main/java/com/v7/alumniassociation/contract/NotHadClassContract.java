package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.ClassListItemBean;

import java.util.List;

/**
 * Created by v7 on 2016/11/8.
 */

public class NotHadClassContract {
    public interface NotHadClassModel extends BaseContract.BaseModel{
        void loadClassList(BaseCallback callback);
    }
    public interface NotHadClassView extends BaseContract.BaseView{
        void onLoadClassListCallback(boolean isSuccess, List<ClassListItemBean> list,boolean isRefreshTop);
    }
    public interface NotHadClassPresenter extends BaseContract.BasePresenter{
        void loadClassList();
    }
}
