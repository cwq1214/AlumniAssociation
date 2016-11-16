package com.v7.alumniassociation.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseRefreshRcvAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by V7 on 2016/11/5.
 */

public class RefreshRecyclerView extends FrameLayout {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.centerMessage)
    TextView centerMessage;


    RecyclerView.LayoutManager manager;
    BaseRefreshRcvAdapter adapter;

    boolean isRefreshingTop = false;
    boolean isRefreshingBottom = false;
    CustomOnRefreshListener customOnRefreshListener;
    SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_refresh_rcv, this, true);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshTop();
            }
        };
        refreshLayout.setOnRefreshListener(onRefreshListener);

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void onRefreshTop(){
        if (isRefreshingTop){
            return;
        }else {
            isRefreshingTop = true;
            if (!refreshLayout.isRefreshing()){
               setRefresh(true);
            }
            customOnRefreshListener.refreshTop();

        }
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void stopRefreshing(){
        setRefresh(false);

    }

    public void refreshTop(){
        onRefreshListener.onRefresh();
    }

    public void refreshTopFinish(){
        setRefresh(false);
    }

    public void refreshBottomFinish(){

    }


//    public void startToRefreshTop(){
//        refreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                refreshLayout.setRefreshing(true);
//            }
//        });
//    }

    public List getDataList(){
        return adapter.getDataList();
    }

    public void setRefresh(final boolean refreshing){
        isRefreshingTop = refreshing;

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });

        if (adapter.getDataList().size()==0){
            centerMessage.setVisibility(VISIBLE);
        }else {
            centerMessage.setVisibility(GONE);
        }
    }

    public void initRcv(RecyclerView.LayoutManager layoutManager, BaseRefreshRcvAdapter adapter){
        recyclerView.setLayoutManager(layoutManager);

        setAdapter(adapter);
    }
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(BaseRefreshRcvAdapter adapter){
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }


    public void setOnRefreshListener(CustomOnRefreshListener onRefreshListener) {
        this.customOnRefreshListener = onRefreshListener;
    }

    public void removeByIndex(int index){
        adapter.getDataList().remove(index);
        adapter.notifyDataSetChanged();
    }

    //下拉刷新后传值
    public void setRefreshedTopList(List dataList){
        adapter.refreshTop(dataList);
        setRefresh(false);
        if (dataList==null||dataList.size()==0){
            centerMessage.setVisibility(VISIBLE);
        }else {
            centerMessage.setVisibility(GONE);
        }
    }
    //上拉加载后传值
    public void setRefreshedBottomList(List dataList){
        adapter.refreshBottom(dataList);
        setRefresh(false);

    }


    public interface CustomOnRefreshListener{
        void refreshTop();
        void refreshBottom(int lastIndex);

    }
}
