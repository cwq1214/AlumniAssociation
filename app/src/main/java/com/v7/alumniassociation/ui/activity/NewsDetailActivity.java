package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.NewsDetailContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.NewsDetailPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/12.
 */

public class NewsDetailActivity extends BaseActivity<NewsDetailContract.NewsDetailPresenter> implements NewsDetailContract.NewsDetailView {
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
    @BindView(R.id.newTitle)
    TextView newTitle;
    @BindView(R.id.createTime)
    TextView createTime;
    @BindView(R.id.likeImg)
    ImageView likeImg;
    @BindView(R.id.likeNum)
    TextView likeNum;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.content)
    TextView content;

    Integer id;

    NewsBean bean;
    boolean isCollection;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected NewsDetailContract.NewsDetailPresenter getPresenterImpl() {
        return new NewsDetailPresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra(IntentHelper.NEWS_ID,0);
        mPresenter.loadNewsDetail(id);

        titleTitle.setText("校园新闻");

        titleFunction.setText("");
        titleFunctionRippleView.setPadding(0, 0, 0, 0);
        titleFunction.setBackgroundResource(R.mipmap.star);
        FrameLayout.LayoutParams functionParam = new FrameLayout.LayoutParams(Dimension.dp2px(getContext(), 24), Dimension.dp2px(getContext(), 24));
        functionParam.gravity = Gravity.CENTER;
        titleFunction.setLayoutParams(functionParam);

    }


    @Override
    public void onLoadNewsDetail(boolean isSuccess, NewsBean newsBean) {
        if (isSuccess&&newsBean!=null){
            bean = newsBean;
            bean.newsId = id;
            newTitle.setText(newsBean.n_title);
            createTime.setText(newsBean.createdTime);
            Glide.with(getContext()).load(newsBean.n_img).into(img);

            likeNum.setText(newsBean.likes+"");
            if (newsBean.liked){
                likeImg.setImageResource(R.mipmap.good);
            }else {
                likeImg.setImageResource(R.mipmap.good_nor);
            }

            content.setText(newsBean.n_content);

            mPresenter.isCollection(bean.newsId);
        }
    }

    @Override
    public void onCollectionClick(boolean isSuccess, boolean collection) {
        if (isSuccess){
            this.isCollection = collection;
            if (collection){
                titleFunction.setBackgroundResource(R.mipmap.star_1);
            }else {
                titleFunction.setBackgroundResource(R.mipmap.star);

            }
        }
    }

    @Override
    public void onDoLick(boolean isSuccess, boolean isLike) {
        if(isSuccess){
//            int like = Integer.parseInt(likeNum.getText().toString());
//            if (isLike){
//                likeImg.setImageResource(R.mipmap.good);
//                likeNum.setText(like+1+"");
//            }else {
//                likeImg.setImageResource(R.mipmap.good_nor);
//                likeNum.setText(like-1+"");
//            }
            mPresenter.loadNewsDetail(bean.newsId);
        }
    }

    @Override
    public void onIsCollectionCallback(boolean isSuccess, boolean isCollection) {
        if (isSuccess){
            this.isCollection = isCollection;
            if (isCollection){
                titleFunction.setBackgroundResource(R.mipmap.star_1);
            }else {
                titleFunction.setBackgroundResource(R.mipmap.star);

            }
        }
    }

    @OnClick(R.id.titleFunction)
    public void onTitleFunctionClick(){
        if (UserInfo.getUserId()==null){
            IntentHelper.openLoginActivity(getContext());
            showToast("请先登陆");
            return;
        }

        if (bean!=null)
            mPresenter.collection(bean,!isCollection);
    }
    @OnClick(R.id.likeImg)
    public void onLikeImg(){
        if (UserInfo.getUserId()==null){
            IntentHelper.openLoginActivity(getContext());
            showToast("请先登陆");
            return;
        }

        mPresenter.doLike(bean.newsId, UserInfo.getUserId(),bean.liked);
    }
}
