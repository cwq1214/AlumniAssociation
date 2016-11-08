package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.contract.LoginContract;
import com.v7.alumniassociation.dialog.LoadingDialog;
import com.v7.alumniassociation.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/7.
 */

public class LoginActivity extends BaseActivity<LoginContract.LoginPresenter> implements LoginContract.LoginView {
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
    @BindView(R.id.inputUserName)
    EditText inputUserName;
    @BindView(R.id.inputPassword)
    EditText inputPassword;
    @BindView(R.id.login)
    TextView login;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginContract.LoginPresenter getPresenterImpl() {
        return new LoginPresenterImpl();
    }

    @Override
    public void onLoginCallback(boolean isSuccess, Object bean) {
        if (isSuccess){
            finish();
        }
    }

    @OnClick(R.id.login)
    public void onLoginClick() {
        if (TextUtils.isEmpty(inputUserName.getText().toString())||TextUtils.isEmpty(inputPassword.getText().toString())){
            showToast("请输入完整信息");
            return;
        }
        mPresenter.login(inputUserName.getText().toString(),inputPassword.getText().toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleTitle.setText("登陆");
        titleFunctionRippleView.setVisibility(View.GONE);
    }
}
