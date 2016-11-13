package com.v7.alumniassociation.contract;

import android.content.Context;

/**
 * Created by V7 on 2016/11/4.
 */

public class BaseContract {
    public interface BaseModel{
        void setContext(Context context);
    }
    public interface BaseView{
        void showToast(String message);
    }
    public interface BasePresenter{
        void attachView(BaseView view);
        void setContext(Context context);
        void onViewCreated();
        void onResume();
        void onDestroy();
    }
}
