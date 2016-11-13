package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.contract.JoinClassContract;
import com.v7.alumniassociation.presenter.JoinClassPresenterImpl;

import butterknife.BindView;

/**
 * Created by v7 on 2016/11/8.
 */

public class JoinClassActivity extends BaseActivity<JoinClassContract.JoinClassPresenter> implements JoinClassContract.JoinClassView {
    @BindView(R.id.titleBack)
    ImageView titleBack;
    @BindView(R.id.titleBackRippleView)
    MaterialRippleLayout titleBackRippleView;
    @BindView(R.id.titleTitle)
    TextView titleTitle;
    @BindView(R.id.titleFunction)
    TextView titleFunction;
    @BindView(R.id.titleFunctionRippleView)
    MaterialRippleLayout titleFunctionRippleView;
    @BindView(R.id.classAvatar)
    ImageView classAvatar;
    @BindView(R.id.className)
    TextView className;
    @BindView(R.id.classLevel)
    TextView classLevel;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.personNumImg)
    ImageView personNumImg;
    @BindView(R.id.personNum)
    TextView personNum;
    @BindView(R.id.adminName)
    TextView adminName;
    @BindView(R.id.introduce)
    TextView introduce;
    @BindView(R.id.submit)
    TextView submit;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_join_class;
    }

    @Override
    protected JoinClassContract.JoinClassPresenter getPresenterImpl() {
        return new JoinClassPresenterImpl();
    }

    @Override
    public void onClassInfoCallback(boolean isSuccess, Class classBean) {

    }

    @Override
    public void onApplyJoinClassCallback(boolean isSuccess) {
        if (isSuccess) {
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleTitle.setText("班级详情");
        titleFunctionRippleView.setVisibility(View.GONE);
    }
}
