package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.PostDetailBean;
import com.v7.alumniassociation.contract.BBSPostDetailContract;
import com.v7.alumniassociation.presenter.BBSPostDetailPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailActivity extends BaseActivity<BBSPostDetailContract.BBSPostDetailPresenter> implements BBSPostDetailContract.BBSPostDetailView {


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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    int postId;
    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_bbs_post_detail;
    }

    @Override
    protected BBSPostDetailContract.BBSPostDetailPresenter getPresenterImpl() {
        return new BBSPostDetailPresenterImpl();
    }

    @Override
    public void onLoadPostCallback(boolean isSuccess, PostDetailBean bean) {
        if (isSuccess){

        }
    }

    @Override
    public void onSendPostCallback(boolean isSuccess) {
        if (isSuccess) {
            mPresenter.loadPost(postId);
        }
    }

}
