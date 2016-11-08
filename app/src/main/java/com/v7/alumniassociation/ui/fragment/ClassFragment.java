package com.v7.alumniassociation.ui.fragment;

import android.view.View;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.contract.BaseContract;
import com.v7.alumniassociation.contract.ClassContract;
import com.v7.alumniassociation.presenter.ClassPresenterImpl;

/**
 * Created by v7 on 2016/11/6.
 */

public class ClassFragment extends BaseFragment<ClassPresenterImpl> implements ClassContract.ClassView{
    @Override
    protected Integer getLayoutResource() {
        return R.layout.fragment_class;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected ClassPresenterImpl getPresenterImpl() {
        return new ClassPresenterImpl();
    }
}
