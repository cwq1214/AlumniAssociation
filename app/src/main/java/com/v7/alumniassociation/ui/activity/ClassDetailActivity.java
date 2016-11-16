package com.v7.alumniassociation.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.ClassDetailContract;
import com.v7.alumniassociation.dialog.ClassMemberOptionalDialog;
import com.v7.alumniassociation.dialog.InputSingleTextDialog;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.ClassDetailPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.util.MediaUtil;
import com.v7.alumniassociation.util.Screen;
import com.v7.alumniassociation.widget.GoView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/8.
 */

public class ClassDetailActivity extends BaseActivity<ClassDetailContract.ClassDetailPresenter> implements ClassDetailContract.ClassDetailView {
    public static Integer classId;
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
    @BindView(R.id.userLayout)
    FlowLayout userLayout;
    @BindView(R.id.labelChooseImg)
    TextView labelChooseImg;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.selImg)
    RelativeLayout selImg;
    @BindView(R.id.modifyClassName)
    GoView modifyClassName;
    @BindView(R.id.modifyClassDepartment)
    GoView modifyClassDepartment;
    @BindView(R.id.modifyClassYear)
    GoView modifyClassYear;
    @BindView(R.id.modifyClassIntroduce)
    GoView modifyClassIntroduce;
    @BindView(R.id.goApplyView)
    GoView goApplyView;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_class_detail;
    }

    @Override
    protected ClassDetailContract.ClassDetailPresenter getPresenterImpl() {
        return new ClassDetailPresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleTitle.setText("班级详情");
        titleFunction.setText("解散班级");
        labelChooseImg.setText("班级图标");


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getClassDetail(classId);


    }

    @Override
    public void onClassDetailCallback(boolean isSuccess, Class aClass) {
        if (isSuccess) {
            setUserAvatar(aClass.userList);
            Glide.with(this).load(aClass.img).into(img);
            modifyClassName.setSubTitleText(aClass.name);
            modifyClassDepartment.setSubTitleText(aClass.series);
            modifyClassIntroduce.setSubTitleText(aClass.introduction);
            modifyClassYear.setSubTitleText(aClass.level);

            System.out.println(UserInfo.getClassId());
            if (UserInfo.getClassId() == null) {
                System.out.println("不是管理员");
                titleFunctionRippleView.setVisibility(View.GONE);
                goApplyView.setVisibility(View.GONE);
            } else {
                System.out.println("是管理员");
                titleFunctionRippleView.setVisibility(View.VISIBLE);
                goApplyView.setVisibility(View.VISIBLE);

            }
        }

    }

    @Override
    public void onModifyClassAvatar(boolean isSuccess, String imageUrl) {
        if (isSuccess) {
            Glide.with(this).load(imageUrl).into(img);
        }
    }

    @Override
    public void onModifyClassName(boolean isSuccess, String className) {
        if (isSuccess) {
            modifyClassName.setSubTitleText(className);
        }
    }

    @Override
    public void onModifyClassDepartment(boolean isSuccess, String department) {
        if (isSuccess) {
            modifyClassDepartment.setSubTitleText(department);
        }
    }

    @Override
    public void onModifyClassLevel(boolean isSuccess, String level) {
        if (isSuccess) {
            modifyClassYear.setSubTitleText(level);
        }
    }

    @Override
    public void onModifyClassIntroduce(boolean isSuccess, String introduce) {
        if (isSuccess) {
            modifyClassIntroduce.setSubTitleText(introduce);
        }
    }

    @Override
    public void onChangeAdministrator(boolean isSuccess, int userId) {
        if (isSuccess) {
            mPresenter.getClassDetail(classId);
        }
    }

    @Override
    public void onRemoveUser(boolean isSuccess, int userId) {
        if (isSuccess) {
            mPresenter.getClassDetail(classId);
        }
    }

    @Override
    public void onDissolveClass(boolean isSuccess) {
        if (isSuccess) {
            finish();
        }
    }

    private void setUserAvatar(List list) {
        userLayout.removeAllViews();
        int sw = Screen.getWidthPixels(getContext());
        int width = sw / 4 - Dimension.dp2px(getContext(), 16);
        for (int i = 0; i < list.size(); i++) {
            final User user = (User) list.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_user_item, null);
            ImageView userAvatar = (ImageView) view.findViewById(R.id.userAvatar);
            TextView userName = (TextView) view.findViewById(R.id.userName);
            Glide.with(this).load(user.img).into(userAvatar);
            userName.setText(user.name);
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(width, width);
            int dp8 = Dimension.dp2px(getContext(), 8);
            params.setMargins(dp8, dp8, dp8, dp8);
            view.setLayoutParams(params);
            if (UserInfo.getClassId() != null && user.userId != UserInfo.getUserId())
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ClassMemberOptionalDialog dialog = new ClassMemberOptionalDialog(getContext());
                        dialog.setTitleText("请选择");
                        dialog.setOnItemClickListener(new ClassMemberOptionalDialog.OnItemClickListener() {
                            @Override
                            public void onChangeAdminClick() {
                                mPresenter.changeAdministrator(classId, user.userId);
                            }

                            @Override
                            public void onRemoveToClassClick() {
                                mPresenter.removeUser(classId, user.userId);
                            }
                        });
                        dialog.show();
                        return true;
                    }
                });
            userLayout.addView(view);

        }
    }


    public void onFunctionClick() {
        new AlertDialog.Builder(getContext())
                .setTitle("确定要解散班级吗?")
                .setMessage("解散后班级无法恢复")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("解散", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.dissolveClass(classId);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void onModifyIntroduceClick() {
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogTitleText("简介");
        dialog.setDialogHint("请输入简介");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                mPresenter.modifyClassIntroduce(classId, inputText);
            }
        });
        dialog.show();
    }

    public void onModifyNameClick() {
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogTitleText("名称");
        dialog.setDialogHint("请输入名称");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                mPresenter.modifyClassName(classId, inputText);
            }
        });
        dialog.show();
    }

    public void onModifyClassYearClick() {
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogTitleText("级别");
        dialog.setDialogHint("请输入级别");
        dialog.setDialogInputStyle(InputType.TYPE_CLASS_NUMBER);
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                mPresenter.modifyClassLevel(classId, inputText);
            }
        });
        dialog.show();
    }

    public void onModifyDepartmentClick() {
        InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogTitleText("系别");
        dialog.setDialogHint("请输入系别");
        dialog.setDialogInputStyle(InputType.TYPE_CLASS_TEXT);
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                mPresenter.modifyClassDepartment(classId, inputText);
            }
        });
        dialog.show();
    }

    public void onSelImgClick() {
        IntentHelper.openSystemAlbumActivityForResult(this);
    }

    public void onGoApplyListClick() {
        IntentHelper.openApplyListActivity(getContext(), classId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentHelper.REQUEST_CODE_GET_IMAGE && resultCode == Activity.RESULT_OK) {
            imageResult(data);
        }
    }

    private void imageResult(Intent data) {
        Uri uri = data.getData();
        Log.e("uri", uri.toString());
        mPresenter.modifyClassAvatar(classId, MediaUtil.getRealFilePath(this, uri));
    }


    @OnClick({R.id.titleFunction, R.id.selImg, R.id.modifyClassName, R.id.modifyClassDepartment, R.id.modifyClassYear, R.id.modifyClassIntroduce, R.id.goApplyView})
    public void onClick(View view) {
        if (UserInfo.getClassId() == null) {
            System.out.println("不是管理员");
            return;
        }
        while (view.getId() == -1) {
            view = (View) view.getParent();
        }
        if (view instanceof MaterialRippleLayout) {
            view = (View) view.getParent();
        }


        switch (view.getId()) {
            case R.id.titleFunction:
                onFunctionClick();
                break;
            case R.id.selImg:
                onSelImgClick();
                break;
            case R.id.modifyClassName:
                onModifyNameClick();
                break;
            case R.id.modifyClassDepartment:
                onModifyDepartmentClick();
                break;
            case R.id.modifyClassYear:
                onModifyClassYearClick();
                break;
            case R.id.modifyClassIntroduce:
                onModifyIntroduceClick();
                break;
            case R.id.goApplyView:
                onGoApplyListClick();
                break;
        }
    }
}
