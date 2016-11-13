package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.contract.SearchClassContract;
import com.v7.alumniassociation.presenter.SearchClassPresenterImpl;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/8.
 */

public class SearchClassActivity extends BaseActivity<SearchClassContract.SearchPresenter> {


    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_search_class;
    }

    @Override
    protected SearchClassContract.SearchPresenter getPresenterImpl() {
        return new SearchClassPresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
