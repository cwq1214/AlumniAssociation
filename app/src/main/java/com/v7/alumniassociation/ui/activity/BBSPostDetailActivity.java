package com.v7.alumniassociation.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.BBSDetailAdapter;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.bean.PostDetailBean;
import com.v7.alumniassociation.bean.ViewType;
import com.v7.alumniassociation.contract.BBSPostDetailContract;
import com.v7.alumniassociation.dialog.InputSingleTextDialog;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.BBSPostDetailPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.viewholder.PostCommentViewHolder;
import com.v7.alumniassociation.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/11.
 */

public class BBSPostDetailActivity extends BaseActivity<BBSPostDetailContract.BBSPostDetailPresenter> implements BBSPostDetailContract.BBSPostDetailView {


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


    BBSDetailAdapter adapter;
    int postId;
    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_bbs_post_detail;
    }

    @Override
    protected BBSPostDetailContract.BBSPostDetailPresenter getPresenterImpl() {
        return new BBSPostDetailPresenterImpl();
    }

    @Override
    public void onLoadPostCallback(boolean isSuccess, PostDetailBean bean) {
        if (isSuccess){
            adapter.setData(postDetailBean2DataList(bean));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSendPostCallback(boolean isSuccess) {
        if (isSuccess) {
            mPresenter.loadPost(postId);
        }
    }

    @Override
    public void onDeletePost(boolean isSuccess) {
        if (isSuccess){
            mPresenter.loadPost(postId);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new BBSDetailAdapter();
        adapter.setOnItemClickListener(new PostCommentViewHolder.OnItemClickListener() {
            @Override
            public boolean onLongClick(final PostDetailBean.Comment data, int index) {
                if (data.userId!=UserInfo.getUserId()){
                    return false;
                }

                new AlertDialog.Builder(getContext())
                        .setTitle("删除评论")
                        .setMessage("删除后别人无法查看到此条信息")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.deletePost(data.commentId);
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
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.VERTICAL, Dimension.dp2px(getContext(),1),getResources().getColor(R.color.borderColor)));
        postId = getIntent().getIntExtra(IntentHelper.POST_ID,0);

        mPresenter.loadPost(postId);

        titleTitle.setText("帖子");
        titleFunction.setText("回帖");
    }

    private List<ViewType> postDetailBean2DataList(PostDetailBean bean){
        List<ViewType> data = new ArrayList<>();

        ViewType head = new ViewType();
        head.type = ViewType.POST_HEAD;
        head.data = bean.bar;
        data.add(head);

        for (int i=0;i<bean.comment.size();i++){
            ViewType item = new ViewType();
            item.type=ViewType.POST_COMMENT;
            item.data = bean.comment.get(i);
            data.add(item);
        }

        return data;
    }
    @OnClick(R.id.titleFunction)
    public void onFunctionClick(){
        if (UserInfo.getUserId()==null){
            IntentHelper.openLoginActivity(getContext());
            showToast("请先登陆");
            return;
        }

        final InputSingleTextDialog dialog = new InputSingleTextDialog(getContext());
        dialog.setDialogTitleText("回帖");
        dialog.setDialogHint("回复内容");
        dialog.setOnDialogDoneListener(new InputSingleTextDialog.OnDialogDoneListener() {
            @Override
            public void onDialogClick(String inputText) {
                mPresenter.sendPost(UserInfo.getUserId(),postId,inputText);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
