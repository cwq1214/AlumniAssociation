package com.v7.alumniassociation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.contract.BaseContract;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by V7 on 2016/11/4.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        ButterKnife.bind(this);
        mPresenter = getPresenterImpl();
        mPresenter.attachView(this);
        mPresenter.setContext(this);
        mPresenter.onViewCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void setView(){
        Integer layoutResource = getLayoutResource();
        if (layoutResource==null){
            View view = getContentView();
            if (view==null){
                throw new RuntimeException("content view empty!");
            }else {
                setContentView(view);
            }
        }else {
            setContentView(layoutResource);
        }
    }

    @Override
    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(message)){
                    return;
                }
                Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Optional
    @OnClick(R.id.titleBack)
    public void onBackClick(){
        finish();
    }

    public Context getContext(){
        return this;
    }
    protected abstract View getContentView();
    protected abstract Integer getLayoutResource();
    protected abstract T getPresenterImpl();

}
