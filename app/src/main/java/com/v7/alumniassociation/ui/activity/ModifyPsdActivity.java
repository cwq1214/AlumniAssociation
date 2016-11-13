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
import com.v7.alumniassociation.contract.ModifyPsdContract;
import com.v7.alumniassociation.presenter.ModifyPsdPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/7.
 */

public class ModifyPsdActivity extends BaseActivity<ModifyPsdContract.ModifyPsdPresenter> implements ModifyPsdContract.ModifyPsdView {
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
    @BindView(R.id.oldPsd)
    EditText oldPsd;
    @BindView(R.id.newPsd1)
    EditText newPsd1;
    @BindView(R.id.newPsd2)
    EditText newPsd2;
    @BindView(R.id.done)
    TextView done;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_modifypsd;
    }

    @Override
    protected ModifyPsdContract.ModifyPsdPresenter getPresenterImpl() {
        return new ModifyPsdPresenterImpl();
    }

    @OnClick(R.id.done)
    public void onDoneClick(){
        if (TextUtils.isEmpty(oldPsd.getText().toString())||TextUtils.isEmpty(newPsd1.getText().toString())||!newPsd1.getText().toString().equals(newPsd2.getText().toString())){
            showToast("请填写好以上信息");
            return;
        }
        if (newPsd1.getText().length()<6){
            showToast("密码长度不小于6位");
            return;
        }
        if(newPsd1.getText().length()>16){
            showToast("密码长度不大于16位");
            return;
        }
        mPresenter.updatePassword(oldPsd.getText().toString(),newPsd1.getText().toString());
    }

    @Override
    public void onUpdatePsdCallback(boolean isSuccess) {
        if (isSuccess){
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        titleTitle.setText("修改密码");
        titleFunctionRippleView.setVisibility(View.GONE);
    }
}
