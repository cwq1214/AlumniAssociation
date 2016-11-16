package com.v7.alumniassociation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.ClassItemAdapter;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.ClassListItemBean;
import com.v7.alumniassociation.contract.NotHadClassContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.NotHadClassPresenterImpl;
import com.v7.alumniassociation.viewholder.ClassItemViewHolder;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/9.
 */

public class NotHadClassFragment extends BaseFragment<NotHadClassContract.NotHadClassPresenter> implements NotHadClassContract.NotHadClassView {
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
    @BindView(R.id.refreshRecyclerView)
    RefreshRecyclerView refreshRecyclerView;

    @Override
    protected Integer getLayoutResource() {
        return R.layout.fragment_not_had_class;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected NotHadClassContract.NotHadClassPresenter getPresenterImpl() {
        return new NotHadClassPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleBack.setImageResource(R.mipmap.search_white);
        titleFunction.setText("创建班级");
        titleTitle.setText("班级");

        mPresenter.loadClassList();

        initView();
    }

    private void initView(){
        refreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ClassItemAdapter adapter = new ClassItemAdapter();
        adapter.setOnItemClickListener(new ClassItemViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(ClassListItemBean aClass, int position) {
                IntentHelper.openJoinClassActivity(getContext(),aClass.id);

            }
        });
        refreshRecyclerView.setAdapter(adapter);
        refreshRecyclerView.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.loadClassList();
            }

            @Override
            public void refreshBottom(int lastIndex) {
                mPresenter.loadClassList();

            }
        });
    }

    @Override
    public void onLoadClassListCallback(boolean isSuccess, List<ClassListItemBean> list, boolean isRefreshTop) {
        if (isSuccess){
            if (isRefreshTop){
                refreshRecyclerView.setRefreshedTopList(list);
            }else {
                refreshRecyclerView.setRefreshedBottomList(list);
            }
        }
    }

    @OnClick(R.id.titleFunction)
    public void onFunctionClick(){
        IntentHelper.openCreateClassActivity(getContext());
    }
    @OnClick(R.id.titleBack)
    public void onTitleFunctionClick(){
        IntentHelper.openSearchClassActivity(getContext());
    }
}
