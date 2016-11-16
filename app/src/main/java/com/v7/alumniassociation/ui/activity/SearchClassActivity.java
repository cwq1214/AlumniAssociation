package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.ClassItemAdapter;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.ClassListItemBean;
import com.v7.alumniassociation.contract.SearchClassContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.SearchClassPresenterImpl;
import com.v7.alumniassociation.viewholder.ClassItemViewHolder;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by v7 on 2016/11/8.
 */

public class SearchClassActivity extends BaseActivity<SearchClassContract.SearchPresenter> implements SearchClassContract.SearchView{


    @BindView(R.id.titleBack)
    ImageView titleBack;
    @BindView(R.id.titleBackRippleView)
    MaterialRippleLayout titleBackRippleView;
    @BindView(R.id.inputSearch)
    EditText inputSearch;
    @BindView(R.id.refreshRecyclerView)
    RefreshRecyclerView refreshRecyclerView;


    String keyword="";
    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_search_class;
    }

    @Override
    protected SearchClassContract.SearchPresenter getPresenterImpl() {
        return new SearchClassPresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRcv();

        mPresenter.getClassList("");

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = s.toString();
                mPresenter.getClassList(keyword);
            }
        });
    }

    private void initRcv(){
        refreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ClassItemAdapter classItemAdapter = new ClassItemAdapter();
        classItemAdapter.setOnItemClickListener(new ClassItemViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(ClassListItemBean aClass, int position) {
                IntentHelper.openJoinClassActivity(getContext(),aClass.id);
            }
        });
        refreshRecyclerView.setAdapter(classItemAdapter);
        refreshRecyclerView.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.getClassList(keyword);
            }

            @Override
            public void refreshBottom(int lastIndex) {
                mPresenter.getClassList(keyword);

            }
        });
    }


    @Override
    public void onClassListClassBack(boolean isSuccess, List<ClassListItemBean> classList) {
        if (isSuccess){
            refreshRecyclerView.setRefreshedTopList(classList);
        }
    }
}
