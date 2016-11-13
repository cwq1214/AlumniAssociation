package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.ApplyListAdapter;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.ApplyItemBean;
import com.v7.alumniassociation.contract.ApplyJoinClassListContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.ApplyJoinClassPresenterImpl;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.viewholder.ApplyItemViewHolder;
import com.v7.alumniassociation.widget.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by v7 on 2016/11/12.
 */

public class ApplyJoinClassListActivity extends BaseActivity<ApplyJoinClassListContract.ApplyJoinClassPresenter> implements ApplyJoinClassListContract.ApplyJoinClassView{
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

    ApplyListAdapter applyListAdapter;
    Integer classId;
    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_apply_list;
    }

    @Override
    protected ApplyJoinClassListContract.ApplyJoinClassPresenter getPresenterImpl() {
        return new ApplyJoinClassPresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleFunction.setText("");
        titleTitle.setText("申请列表");

        classId = getIntent().getIntExtra(IntentHelper.CLASS_ID,0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        applyListAdapter = new ApplyListAdapter();
        applyListAdapter.setOnAgreeClickListener(new ApplyItemViewHolder.OnAgreeClickListener() {
            @Override
            public void onAgreeClick(ApplyItemBean bean, int index) {
                mPresenter.agree(bean.applyId,classId,"1");
            }
        });
        recyclerView.setAdapter(applyListAdapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.VERTICAL, Dimension.dp2px(getContext(),1),getResources().getColor(R.color.borderColor)));

        mPresenter.loadList(classId);
    }

    @Override
    public void onLoadListCallback(boolean isSuccess, List<ApplyItemBean> been) {
        if (isSuccess){
            if (been!=null)
                applyListAdapter.setBeen(been);
                applyListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void oiAgreeCallback(boolean isSuccess) {
        mPresenter.loadList(classId);
    }
}
