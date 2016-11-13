package com.v7.alumniassociation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.BBSAdapter;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.bean.BBSPostItemBean;
import com.v7.alumniassociation.contract.BBSContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.BBSPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.viewholder.BBSPostItemViewHolder;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/7.
 */

public class BBSFragment extends BaseFragment<BBSContract.BBSPresenter> implements BBSContract.BBSView {
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
        return R.layout.fragment_bbs;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected BBSContract.BBSPresenter getPresenterImpl() {
        return new BBSPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTitle.setText("校园论坛");
        titleFunctionRippleView.setPadding(0,0,0,0);
        titleFunction.setBackgroundResource(R.mipmap.note);
        MaterialRippleLayout.LayoutParams params = new MaterialRippleLayout.LayoutParams(Dimension.dp2px(getContext(),25),Dimension.dp2px(getContext(),25));
        params.gravity = Gravity.CENTER;
        titleFunction.setLayoutParams(params);
        initRefreshView();
        titleBackRippleView.setVisibility(View.GONE
        );

        refreshRecyclerView.refreshTop();
    }

    private void initRefreshView(){
        refreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        BBSAdapter adapter = new BBSAdapter();
        adapter.setOnItemClickListener(new BBSPostItemViewHolder.OnItemClickListener() {
            @Override
            public void onClick(BBSPostItemBean data, int index) {

            }

            @Override
            public boolean onLongClick(BBSPostItemBean data, int index) {

                return false;
            }
        });
        refreshRecyclerView.setAdapter(adapter);

        refreshRecyclerView.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.refreshBBSList(-1);
            }

            @Override
            public void refreshBottom(int lastIndex) {
                BBSPostItemBean bbsPostItemBean = (BBSPostItemBean) refreshRecyclerView.getDataList().get(lastIndex);
                mPresenter.refreshBBSList(bbsPostItemBean.barId
                );

            }
        });
    }

    @Override
    public void onFreshBBS(boolean isSuccess, List<BBSPostItemBean> list, boolean isRefreshTop) {
        if (isSuccess){
            if (isRefreshTop){
                refreshRecyclerView.setRefreshedTopList(list);
            }else {
                refreshRecyclerView.setRefreshedBottomList(list);
            }
        }else {
            refreshRecyclerView.stopRefreshing();
        }
    }

    @Override
    public void onDeleteCallback(boolean isSuccess, int deleteId) {
        List<BBSPostItemBean> list = refreshRecyclerView.getDataList();
        for (int i=0;i<list.size();i++){
            if (list.get(i).barId ==deleteId){
                refreshRecyclerView.removeByIndex(i);
                return;
            }
        }
    }
    @OnClick(R.id.titleFunction)
    public void onFunctionClick(){
        if (UserInfo.getUserId()==null){
            IntentHelper.openLoginActivity(getContext());
            return;
        }
        IntentHelper.openSendPostActivity(getContext());
    }
}
