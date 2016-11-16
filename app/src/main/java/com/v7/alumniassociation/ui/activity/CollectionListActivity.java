package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.NewsAdapter;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.CollectionListContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.CollectionPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.viewholder.NewsViewHolder;
import com.v7.alumniassociation.widget.RecycleViewDivider;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v7 on 2016/11/13.
 */

public class CollectionListActivity extends BaseActivity<CollectionListContract.CollectionListPresenter> implements CollectionListContract.CollectionListView {
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
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_collection_list;
    }

    @Override
    protected CollectionListContract.CollectionListPresenter getPresenterImpl() {
        return new CollectionPresenterImpl();
    }


    @Override
    public void onLoadCollectionListCallback(boolean isSuccess, List<NewsBean> newsBeen) {
        if (isSuccess) {
            refreshRecyclerView.setRefreshedTopList(newsBeen);
        }else {
            refreshRecyclerView.stopRefreshing();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();
        titleTitle.setText("我的收藏");



        mPresenter.loadCollectionList(UserInfo.getUserId());
    }

    private void init(){
        refreshRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, Dimension.dp2px(getContext(),1),getResources().getColor(R.color.borderColor)));
        refreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        NewsAdapter adapter = new NewsAdapter();
        adapter.setOnItemClickListener(new NewsViewHolder.OnItemClickListener() {
            @Override
            public void onNewsClick(NewsBean bean) {
                IntentHelper.openNewsDetailActivity(getContext(),bean.newsId);
            }
        });
        refreshRecyclerView.setAdapter(adapter);
        refreshRecyclerView.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.loadCollectionList(UserInfo.getUserId());
            }

            @Override
            public void refreshBottom(int lastIndex) {
                mPresenter.loadCollectionList(UserInfo.getUserId());
            }
        });
    }
}
