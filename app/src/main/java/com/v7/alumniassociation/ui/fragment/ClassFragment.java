package com.v7.alumniassociation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.contract.ClassContract;
import com.v7.alumniassociation.presenter.ClassPresenterImpl;

import butterknife.BindView;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassFragment extends BaseFragment<ClassContract.ClassPresenter> implements ClassContract.ClassView {

    Class aClass;

    HadClassFragment hadClassFragment = new HadClassFragment();
    NotHadClassFragment notHadClassFragment = new NotHadClassFragment();

    @Override
    protected Integer getLayoutResource() {
        return R.layout.layout_empty;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected ClassContract.ClassPresenter getPresenterImpl() {
        return new ClassPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.hadClass();
    }

    @Override
    public void onHadClassCallback(boolean isSuccess, Class aClass) {
        if (isSuccess){
            this.aClass = aClass;
            if (hadClass()){
                hadClassFragment.aClass = aClass;
                getChildFragmentManager().beginTransaction().replace(R.id.layout,hadClassFragment).commit();
            }else {
                getChildFragmentManager().beginTransaction().replace(R.id.layout,notHadClassFragment).commit();
            }
        }
    }


    private boolean hadClass() {
        if (aClass != null && aClass.classId != null) {
            return true;
        }
        return false;
    }
}
