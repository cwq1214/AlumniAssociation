package com.v7.alumniassociation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.contract.UploadImgContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.UploadImgPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.util.MediaUtil;
import com.v7.alumniassociation.util.Screen;

import org.apmem.tools.layouts.FlowLayout;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/10.
 */

public class UploadImgActivity extends BaseActivity<UploadImgContract.UploadImgPresenter> implements UploadImgContract.UploadImgView {
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
    @BindView(R.id.addImg)
    FrameLayout addImg;
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout;


    List<String> imgs = new ArrayList<>();
    public static Integer classId ;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_upload_img;
    }

    @Override
    protected UploadImgContract.UploadImgPresenter getPresenterImpl() {
        return new UploadImgPresenterImpl();
    }

    @Override
    public void uploadImgCallback(boolean isSuccess) {
        if (isSuccess) {
            finish();
        }
    }

    @OnClick(R.id.addImg)
    public void onAddImgClick(){
        IntentHelper.openSystemAlbumActivityForResult(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addImg.setLayoutParams(getImageViewLayoutParams());
        titleTitle.setText("上传照片");
        titleFunction.setText("发送");
    }

    @OnClick(R.id.titleFunction)
    public void onTitleFunctionClick(){
        mPresenter.uploadImg(imgs,classId, UserInfo.getUserId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IntentHelper.REQUEST_CODE_GET_IMAGE&&resultCode== Activity.RESULT_OK){
            imageResult(data);
        }
    }

    private void imageResult(Intent data){
        Uri uri = data.getData();
        Log.e("uri", uri.toString());

        String filePath = MediaUtil.getRealFilePath(this,uri);
        imgs.add(filePath);
//        mPresenter.uploadImg(MediaUtil.getRealFilePath(this,uri));
        addPreviewImgItem(filePath);
    }

    private void addPreviewImgItem(String img){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(getResources().getColor(R.color.borderColor));
        imageView.setLayoutParams(getImageViewLayoutParams());
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                flowLayout.removeView(v);
                return true;
            }
        });
        Glide.with(this).load(img).into(imageView);
        flowLayout.addView(imageView,flowLayout.getChildCount()-1);
    }

    private FlowLayout.LayoutParams getImageViewLayoutParams(){
        int itemWidth = (Screen.getWidthPixels(getContext())-Dimension.dp2px(getContext(),16))/4;
        int margin = Dimension.dp2px(getContext(),8);

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(itemWidth-margin*2,itemWidth-margin*2);
        params.setGravity(Gravity.CENTER);
        params.setMargins(margin,margin,margin,margin);

        return params;
    }
}
