package com.v7.alumniassociation.ui.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.PersonalInfoContract;
import com.v7.alumniassociation.dialog.InputSingleTextDialog;
import com.v7.alumniassociation.dialog.SingleChooseDialog;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.PersonalInfoPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.MediaUtil;
import com.v7.alumniassociation.widget.GoView;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalInfoActivity extends BaseActivity<PersonalInfoContract.PersonalInfoPresenter> implements PersonalInfoContract.PersonalInfoView {
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
    @BindView(R.id.modifyName)
    GoView modifyName;
    @BindView(R.id.modifyGender)
    GoView modifyGender;
    @BindView(R.id.modifyNo)
    GoView modifyNo;
    @BindView(R.id.modifyDepartment)
    GoView modifyDepartment;
    @BindView(R.id.modifyPhone)
    GoView modifyPhone;
    @BindView(R.id.modifyIntroduce)
    GoView modifyIntroduce;
    @BindView(R.id.modifyPsd)
    GoView modifyPsd;
    @BindView(R.id.logout)
    TextView logout;

    private final int type_headAvatar=1;
    private final int type_name=2;
    private final int type_gender=3;
    private final int type_no=4;
    private final int type_department=5;
    private final int type_tel=6;
    private final int type_introduce=7;
    int modifyType =0;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_personalinfo;
    }

    @Override
    protected PersonalInfoContract.PersonalInfoPresenter getPresenterImpl() {
        return new PersonalInfoPresenterImpl();
    }

    @Override
    public void uploadImgCallback(boolean isSuccess, String url) {
        if (isSuccess) {
            Glide.with(this).load(url).into(img);
        }
    }

    @Override
    public void updateUserInfoCallback(boolean isSuccess, String updateString) {
        if (isSuccess) {
            switch (modifyType){
                case type_department:
                    modifyDepartment.setSubTitleText(updateString);
                    break;
                case type_gender:
                    modifyGender.setSubTitleText(updateString);
                    break;
                case type_introduce:
                    modifyIntroduce.setSubTitleText(updateString);
                    break;
                case type_name:
                    modifyName.setSubTitleText(updateString);
                    break;
                case type_no:
                    modifyNo.setSubTitleText(updateString);
                    break;
                case type_tel:
                    modifyPhone.setSubTitleText(updateString);
                    break;
            }
        }
    }

    @Override
    public void onUserInfoCallback(boolean isSuccess, User user) {
        if (isSuccess){
            modifyName.setSubTitleText(user.name);
            modifyDepartment.setSubTitleText(user.series);
            modifyGender.setSubTitleText(user.sex);
            modifyIntroduce.setSubTitleText(user.introduction);
            modifyNo.setSubTitleText(user.no);
            modifyPhone.setSubTitleText(user.tel);
            if (!TextUtils.isEmpty(user.img)){
                Glide.with(this).load(user.img).into(img);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUserInfo();
    }

    private void initView() {
        labelChooseImg.setText("头像");

        titleTitle.setText("个人信息");
        titleFunctionRippleView.setVisibility(View.GONE);

    }

    @OnClick(R.id.logout)
    public void onLogOutClick(){
        UserInfo.removeAll();
        IntentHelper.backToMainActivity(getContext());
    }

    @OnClick(R.id.selImg)
    public void onSelImgClick(){

        IntentHelper.openSystemAlbumActivityForResult(this);
    }
    @OnClick(R.id.modifyIntroduce)
    public void onModifyIntroduceClick(){

        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogHint("请输入个人简介");
        dialog.setDialogTitleText("个人简介");
        dialog.setCanNull(true);
        dialog.setDialogInputStyle(InputType.TYPE_CLASS_TEXT);
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                User user = new User();
                user.introduction = inputText;
                modifyType = type_introduce;
                mPresenter.updateUserInfo("introduction",inputText);
            }
        });
        dialog.show();
    }

    @OnClick(R.id.modifyDepartment)
    public void onModifyDepartmentClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogHint("请输入系别");
        dialog.setDialogTitleText("系别");
        dialog.setCanNull(false);
        dialog.setDialogInputStyle(InputType.TYPE_CLASS_TEXT);
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                User user = new User();
                user.series = inputText;
                modifyType = type_department;
                mPresenter.updateUserInfo("series",inputText);
            }
        });
        dialog.show();
    }

    @OnClick(R.id.modifyPhone)
    public void onModifyPhoneClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogHint("请输入联系电话");
        dialog.setDialogTitleText("联系电话");
        dialog.setCanNull(false);
        dialog.setDialogInputStyle(InputType.TYPE_CLASS_TEXT);
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                User user = new User();
                user.tel = inputText;
                modifyType = type_tel;
                mPresenter.updateUserInfo("tel",inputText);
            }
        });
        dialog.show();
    }

    @OnClick(R.id.modifyPsd)
    public void onModifyPsdClick(){
        IntentHelper.openModifyPsdActivity(this);
    }

    @OnClick(R.id.modifyGender)
    public void onModifyGenderClick(){
        SingleChooseDialog dialog = new SingleChooseDialog(this);
        dialog.setTitleText("请选择性别");
        dialog.setOnDoneClickListener(new SingleChooseDialog.OnDoneClickListener() {
            @Override
            public void onDoneClick(String text, int index) {
                modifyType = type_gender;
                mPresenter.updateUserInfo("sex",text);
            }
        });
        dialog.addItem("男",true);
        dialog.addItem("女",false);
        dialog.show();
    }

    @OnClick(R.id.modifyName)
    public void onModifyNameClick(){
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogHint("请输入名字");
        dialog.setDialogTitleText("名字");
        dialog.setCanNull(false);
        dialog.setDialogInputStyle(InputType.TYPE_CLASS_TEXT);
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                User user = new User();
                user.name = inputText;
                modifyType = type_name;
                mPresenter.updateUserInfo("name",inputText);
            }
        });
        dialog.show();
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
        mPresenter.uploadImg(MediaUtil.getRealFilePath(this,uri));
    }
}
