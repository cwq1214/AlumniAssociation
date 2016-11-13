package com.v7.alumniassociation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.NewsAdapter;
import com.v7.alumniassociation.adapter.ViewViewPagerAdapter;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.HomeContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.HomePresenterImpl;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.viewholder.NewsViewHolder;
import com.v7.alumniassociation.widget.NoScrollViewPager;
import com.v7.alumniassociation.widget.RecycleViewDivider;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by V7 on 2016/11/4.
 */

public class HomeFragment extends BaseFragment<HomeContract.HomePresenter> implements HomeContract.HomeView {
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
    @BindView(R.id.rbNews)
    RadioButton rbNews;
    @BindView(R.id.rbFengCai)
    RadioButton rbFengCai;
    @BindView(R.id.head_rg)
    RadioGroup headRg;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;


    RefreshRecyclerView newsRcv;
    RefreshRecyclerView fengcaiRcv;
    @Override
    protected Integer getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected HomeContract.HomePresenter getPresenterImpl() {
        return new HomePresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleBack.setImageResource(R.mipmap.search_white);
        titleFunction.setText("");
        titleFunctionRippleView.setPadding(0, 0, 0, 0);
        titleFunction.setBackgroundResource(R.mipmap.msm);
        FrameLayout.LayoutParams functionParam = new FrameLayout.LayoutParams(Dimension.dp2px(getContext(), 24), Dimension.dp2px(getContext(), 24));
        functionParam.gravity = Gravity.CENTER;
        titleFunction.setLayoutParams(functionParam);
        titleTitle.setText("掌上华软");

        viewPager.setAdapter(new ViewViewPagerAdapter(getViewPagerViews()));

        mPresenter.refreshNewsList(-1);
    }

    private List<View> getViewPagerViews(){
        List<View> views = new ArrayList<>();
        initNewRcv();
        views.add(newsRcv);
        initFengCaiRcv();
        views.add(fengcaiRcv);
        return views;
    }

    private void initNewRcv(){
        newsRcv = new RefreshRecyclerView(getContext(),null);
        newsRcv.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL,Dimension.dp2px(getContext(),1),getResources().getColor(R.color.borderColor)));
        newsRcv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        NewsAdapter adapter = new NewsAdapter();
        adapter.setOnItemClickListener(new NewsViewHolder.OnItemClickListener() {
            @Override
            public void onNewsClick(NewsBean bean) {
                IntentHelper.openNewsDetailActivity(getContext(),bean.newsId);
            }
        });
        newsRcv.setAdapter(adapter);
        newsRcv.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.refreshNewsList(-1);
            }

            @Override
            public void refreshBottom(int lastIndex) {
                NewsBean bean = (NewsBean) newsRcv.getDataList().get(lastIndex);
                mPresenter.refreshNewsList(bean.newsId);
            }
        });
    }

    private void initFengCaiRcv(){
        fengcaiRcv = new RefreshRecyclerView(getContext(),null);
    }


    @Override
    public void onRefreshNewsCallback(boolean isSuccess, List<NewsBean> newsBeen, boolean isRefreshTop) {
        if (isSuccess){
            if (isRefreshTop){
                newsRcv.setRefreshedTopList(newsBeen);
            }else {
                newsRcv.setRefreshedBottomList(newsBeen);
            }
        }else {
            newsRcv.stopRefreshing();
        }
    }

    @Override
    public void onRefreshFengCaiCallback(boolean isSuccess, List newsBeen, boolean isRefreshTop) {

    }
}
