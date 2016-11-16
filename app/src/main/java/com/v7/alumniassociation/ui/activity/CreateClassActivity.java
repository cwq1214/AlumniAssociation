package com.v7.alumniassociation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.CreateClassBean;
import com.v7.alumniassociation.contract.CreateClassContract;
import com.v7.alumniassociation.dialog.InputSingleTextDialog;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.CreateClassPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.MediaUtil;
import com.v7.alumniassociation.widget.GoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/8.
 */

public class CreateClassActivity extends BaseActivity<CreateClassContract.CreateClassPresenter> implements CreateClassContract.CreateClassView{
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
    @BindView(R.id.labelChooseImg)
    TextView labelChooseImg;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.selImg)
    RelativeLayout selImg;
    @BindView(R.id.className)
    GoView className;
    @BindView(R.id.classDepartment)
    GoView classDepartment;
    @BindView(R.id.classLevel)
    GoView classLevel;
    @BindView(R.id.classIntroduce)
    GoView classIntroduce;
    @BindView(R.id.submit)
    TextView submit;


    String imgPath;
    String level;
    String department;
    String introduce;
    String name;
    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_create_class;
    }

    @Override
    protected CreateClassContract.CreateClassPresenter getPresenterImpl() {
        return new CreateClassPresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        labelChooseImg.setText("班级头像");
        titleTitle.setText("创建班级");
    }

    @OnClick(R.id.selImg)
    public void onSelImgClick(){
        IntentHelper.openSystemAlbumActivityForResult(this);
    }

    @OnClick(R.id.className)
    public void onClassNameClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(this);
        dialog.setDialogTitleText("名称");
        dialog.setDialogHint("请输入班级名称");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                className.setSubTitleText(inputText);
                name = inputText;
            }
        });
        dialog.show();
    }

    @OnClick(R.id.classDepartment)
    public void onClassDepartmentClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(this);
        dialog.setDialogTitleText("系别");
        dialog.setDialogHint("请输入系别名称");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                classDepartment.setSubTitleText(inputText);
                department = inputText;
            }
        });
        dialog.show();
    }
    @OnClick(R.id.classIntroduce)
    public void onClassIntroduceClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(this);
        dialog.setDialogTitleText("级别");
        dialog.setDialogHint("请输入级别名称");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                classIntroduce.setSubTitleText(inputText);
                introduce = inputText;
            }
        });
        dialog.show();
    }
    @OnClick(R.id.classLevel)
    public void onClassLevelClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(this);
        dialog.setDialogTitleText("级别");
        dialog.setDialogHint("请输入级别名称");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                classLevel.setSubTitleText(inputText);
                level = inputText;
            }
        });
        dialog.show();
    }

    @OnClick(R.id.submit)
    public void onSubmitClick(){
        if (TextUtils.isEmpty(imgPath)||TextUtils.isEmpty(name)||TextUtils.isEmpty(level)||TextUtils.isEmpty(department)||TextUtils.isEmpty(introduce)){
            showToast("请填写完整信息");
            return;
        }
        CreateClassBean bean = new CreateClassBean();
        bean.img = imgPath;
        bean.introduction = introduce;
        bean.level = level;
        bean.name = name;
        bean.series = department;
        bean.userId = UserInfo.getUserId();
        mPresenter.submitApplyToCreateClass(bean);
    }

    @Override
    public void onCreateClassCallback(boolean isSuccess) {
        if (isSuccess){
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IntentHelper.REQUEST_CODE_GET_IMAGE&&resultCode== Activity.RESULT_OK){
            imageResult(data);
        }
    }
    private void imageResult(Intent data){
        Uri uri = data.getData();
        Log.e("uri", uri.toString());
        imgPath = MediaUtil.getRealFilePath(this,uri);
        Glide.with(this).load(imgPath).into(img);
    }

}
