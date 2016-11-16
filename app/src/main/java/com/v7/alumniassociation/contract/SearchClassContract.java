package com.v7.alumniassociation.contract;

import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.ClassListItemBean;

import java.util.List;

/**
 * Created by v7 on 2016/11/8.
 */

public class SearchClassContract {
    public interface SearchModel extends BaseContract.BaseModel{
        void searchClass(String searchKey, BaseCallback callback);
    }
    public interface SearchView extends BaseContract.BaseView{
        void onClassListClassBack(boolean isSuccess, List<ClassListItemBean> classList);
    }
    public interface SearchPresenter extends BaseContract.BasePresenter{
        void getClassList(String keyword);
    }
}
