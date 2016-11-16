package com.v7.alumniassociation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.bean.User;
import com.v7.alumniassociation.contract.PersonalContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.PersonalPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.widget.GoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/7.
 */

public class PersonalFragment extends BaseFragment<PersonalContract.PersonalPresenter> implements PersonalContract.PersonalView {
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
    @BindView(R.id.userAvatar)
    ImageView userAvatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userNum)
    TextView userNum;
    @BindView(R.id.fansList)
    GoView fansList;
    @BindView(R.id.focusList)
    GoView focusList;
    @BindView(R.id.collectionList)
    GoView collectionList;

    @Override
    protected Integer getLayoutResource() {
        return R.layout.fragment_personal;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected PersonalContract.PersonalPresenter getPresenterImpl() {
        return new PersonalPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTitle.setText("个人中心");
        titleBackRippleView.setVisibility(View.GONE);
        titleFunctionRippleView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getPersonalCenterViewData();
    }

    @Override
    public void onDataCallback(boolean isSuccess, User user) {
        if (isSuccess){
            String userName = UserInfo.getUserName();
            this.userName.setText(userName);

            String userNo = UserInfo.getNo();
            this.userNum.setText(userNo);

            String avatar = UserInfo.getImg();

            if (!TextUtils.isEmpty(avatar))
                Glide.with(this).load(avatar).into(userAvatar);

            if (user.fansCount!=0)
            fansList.setSubTitleText(String.valueOf(user.fansCount));

            if (user.followCount!=0)
            focusList.setSubTitleText(String.valueOf(user.followCount));
        }
    }

    @OnClick(R.id.personalHeadView)
    public void onHeadViewClick(){
        IntentHelper.openPersonalInfoActivity(getContext());
    }

    @OnClick(R.id.focusList)
    public void onFocusListClick(){
        showToast("敬请期待");
    }

    @OnClick(R.id.fansList)
    public void onFansListClick(){
        showToast("敬请期待");
    }

    @OnClick(R.id.collectionList)
    public void onCollectionListClick(){
        IntentHelper.openCollectionListActivity(getContext());
    }
}
