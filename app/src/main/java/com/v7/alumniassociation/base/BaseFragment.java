package com.v7.alumniassociation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.v7.alumniassociation.contract.BaseContract;

import butterknife.ButterKnife;

/**
 * Created by V7 on 2016/11/4.
 */

public abstract class BaseFragment <T extends BaseContract.BasePresenter> extends Fragment implements BaseContract.BaseView{


    protected View rootView;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getRootView();
        if (rootView == null) {
            if (view == null) {
                Integer layoutResource = getLayoutResource();
                if (layoutResource==null){
                    throw new RuntimeException();
                }else {
                    rootView = inflater.inflate(getLayoutResource(), null);
                }
            } else {
                rootView = view;
            }
            ButterKnife.bind(this,rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = getPresenterImpl();
        mPresenter.attachView(this);
        mPresenter.setContext(getContext());
        mPresenter.onViewCreated();

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showToast(String message) {
        if (TextUtils.isEmpty(message))
            return;
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    protected abstract Integer getLayoutResource();
    protected abstract View getRootView();
    protected abstract T getPresenterImpl();
}
