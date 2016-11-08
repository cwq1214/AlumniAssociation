package com.v7.alumniassociation.ui.fragment;

import android.view.View;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.contract.BBSContract;
import com.v7.alumniassociation.presenter.BBSPresenterImpl;

/**
 * Created by v7 on 2016/11/7.
 */

public class BBSFragment extends BaseFragment<BBSContract.BBSPresenter> implements BBSContract.BBSView{
    @Override
    protected Integer getLayoutResource() {
        return R.layout.fragment_bbs;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected BBSContract.BBSPresenter getPresenterImpl() {
        return new BBSPresenterImpl();
    }
}
