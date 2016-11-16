package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.contract.JoinClassContract;
import com.v7.alumniassociation.dialog.InputSingleTextDialog;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.JoinClassPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

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

    int classId;

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
        if (isSuccess){
            className.setText(classBean.name);
            Glide.with(this).load(classBean.img).into(classAvatar);
            classLevel.setText(classBean.level);
            introduce.setText(classBean.introduction);
            department.setText(classBean.series);
            personNum.setText(classBean.userList.size()+"");
            adminName.setText(classBean.admin);

        }
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

        classId = getIntent().getIntExtra(IntentHelper.CLASS_ID,0);

        mPresenter.getClassInfo(classId);
    }

    @OnClick(R.id.submit)
    public void onJoinClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogTitleText("理由");
        dialog.setDialogHint("请输入理由");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                mPresenter.applyJoinClass(classId,inputText);
            }
        });
        dialog.show();
    }
}
