package com.v7.alumniassociation.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.ClassImgAlbumAdapter;
import com.v7.alumniassociation.adapter.MsgBoardAdapter;
import com.v7.alumniassociation.adapter.ViewViewPagerAdapter;
import com.v7.alumniassociation.base.BaseFragment;
import com.v7.alumniassociation.bean.Class;
import com.v7.alumniassociation.bean.MsgBoardItem;
import com.v7.alumniassociation.bean.PhotoAlbumItem;
import com.v7.alumniassociation.contract.HadClassContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.HadClassPresenterImpl;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.viewholder.ImageViewHolder;
import com.v7.alumniassociation.viewholder.MsgBoardItemViewHolder;
import com.v7.alumniassociation.widget.GridLayoutDivider;
import com.v7.alumniassociation.widget.NoScrollViewPager;
import com.v7.alumniassociation.widget.RecycleViewDivider;
import com.v7.alumniassociation.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by v7 on 2016/11/6.
 */

public class HadClassFragment extends BaseFragment<HadClassPresenterImpl> implements HadClassContract.HadClassView {


    RefreshRecyclerView msgBorder;
    RefreshRecyclerView photoAlbum;

    public static Class aClass;
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
    @BindView(R.id.rbMsgBoard)
    RadioButton rbMsgBoard;
    @BindView(R.id.rbXiangChe)
    RadioButton rbXiangChe;
    @BindView(R.id.head_rg)
    RadioGroup headRg;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;


    MsgBoardAdapter adapter = new MsgBoardAdapter();
    ClassImgAlbumAdapter imgAdapter = new ClassImgAlbumAdapter();

    @Override
    protected Integer getLayoutResource() {
        return R.layout.fragment_had_class;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected HadClassPresenterImpl getPresenterImpl() {
        return new HadClassPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initHadClassView();
        msgBorder.refreshTop();
        photoAlbum.refreshTop();
    }

    private List<View> getViews() {
        List<View> views = new ArrayList<>();

        initMsgBoardRcv();

        views.add(msgBorder);

        initImgRcv();

        views.add(photoAlbum);

        return views;
    }

    private void initMsgBoardRcv() {
        msgBorder = new RefreshRecyclerView(getContext());
        msgBorder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        msgBorder.setAdapter(adapter);
        adapter.setOnDeleteClickListener(new MsgBoardItemViewHolder.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final MsgBoardItem data, int index) {
                new AlertDialog.Builder(getContext())
                        .setTitle("确定要删除吗？")
                        .setMessage("删除后数据无法恢复")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.deleteMsgBoardById(data.msgboardId);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
        msgBorder.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, Dimension.dp2px(getContext(), 1), getResources().getColor(R.color.borderColor)));
        msgBorder.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.refreshMsgBoard(aClass.classId, -1);
            }

            @Override
            public void refreshBottom(int lastIndex) {
                List msgBoardItems = msgBorder.getDataList();

                Object object = msgBoardItems.get(msgBoardItems.size() - 1);

                mPresenter.refreshMsgBoard(aClass.classId, ((MsgBoardItem) object).msgboardId);

            }
        });

        photoAlbum = new RefreshRecyclerView(getContext());
    }

    private void initImgRcv() {
        photoAlbum = new RefreshRecyclerView(getContext());

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        photoAlbum.setLayoutManager(layoutManager);


        imgAdapter = new ClassImgAlbumAdapter();

        imgAdapter.setOnImgClickListener(new ImageViewHolder.OnImgClickListener() {
            @Override
            public void onClick(PhotoAlbumItem item, int position) {
                IntentHelper.openBrowseImageActivity(getContext(),item.imgUrl);
            }

            @Override
            public void onLongClick(PhotoAlbumItem item, int position) {

            }
        });

        photoAlbum.setAdapter(imgAdapter);

        photoAlbum.setOnRefreshListener(new RefreshRecyclerView.CustomOnRefreshListener() {
            @Override
            public void refreshTop() {
                mPresenter.refreshPhotoAlbum(aClass.classId, -1);
            }

            @Override
            public void refreshBottom(int lastIndex) {
//                List list = msgBorder.getDataList();
//
//                Object object = list.get(list.size()-1);
//                mPresenter.refreshPhotoAlbum(aClass.classId, ((PhotoAlbumItem) object).barId);
            }
        });

    }

    @OnClick(R.id.titleBack)
    public void onBackBtnClick() {
        IntentHelper.openSendClassMessageActivity(getContext(), aClass.classId);
    }

    @OnLongClick(R.id.titleBack)
    public boolean onBackBtnLongClick() {
        IntentHelper.openClassMsgBoardUploadImgActivity(getContext(), aClass.classId);
        return true;
    }


    @OnClick(R.id.titleFunction)
    public void onFunctionBtnClick() {
        IntentHelper.openClassDetailActivity(getContext(), aClass.classId);
    }


    private void initHadClassView() {
        titleTitle.setText("班级");
        titleBack.setImageResource(R.mipmap.pen);
        titleFunction.setText("");
        titleFunction.setBackgroundResource(R.mipmap.suff);

        headRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMsgBoard:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rbXiangChe:
                        viewPager.setCurrentItem(1);
                        break;
                }

            }
        });

        viewPager.setAdapter(new ViewViewPagerAdapter(getViews()));
    }


    @Override
    public void onRefreshMsgBoardCallback(boolean isSuccess, List<MsgBoardItem> msgBoardItems, boolean isRefreshTop) {
        if (isSuccess) {
            if (isRefreshTop) {
                msgBorder.setRefreshedTopList(msgBoardItems);
            } else {
                msgBorder.setRefreshedBottomList(msgBoardItems);
            }
        } else {
            msgBorder.stopRefreshing();
        }

    }

    @Override
    public void onDeleteMsgBoardById(boolean isSuccess) {
        mPresenter.refreshMsgBoard(aClass.classId, -1);
    }

    @Override
    public void refreshPhotoAlbum(boolean isSuccess, List<PhotoAlbumItem> photoAlbumItems, boolean isRefreshTop) {
        if (isSuccess) {
            if (isRefreshTop) {
                photoAlbum.setRefreshedTopList(photoAlbumItems);
            } else {
                photoAlbum.setRefreshedBottomList(photoAlbumItems);
            }
        } else {
            photoAlbum.stopRefreshing();
        }
    }

}
