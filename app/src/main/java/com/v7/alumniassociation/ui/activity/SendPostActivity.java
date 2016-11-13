package com.v7.alumniassociation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.contract.SendPostContract;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.presenter.SendPostPresenterImpl;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.util.MediaUtil;
import com.v7.alumniassociation.util.Screen;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/12.
 */

public class SendPostActivity extends BaseActivity<SendPostContract.SendPostPresenter> implements SendPostContract.SendPostView {


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
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.currentText)
    TextView currentText;
    @BindView(R.id.addImg)
    FrameLayout addImg;
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout;

    List<String> imgUrl = new ArrayList<>();
    List<String> localImgPath = new ArrayList<>();
    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_send_post;
    }

    @Override
    protected SendPostContract.SendPostPresenter getPresenterImpl() {
        return new SendPostPresenterImpl();
    }

    @Override
    public void uploadImage(boolean isSuccess, List<String> images) {
        if (isSuccess) {
            this.imgUrl = images;
            mPresenter.uploadPost(imgUrl, UserInfo.getUserId(), edittext.getText().toString());
        }
    }

    @Override
    public void uploadPost(boolean isSuccess) {
        if (isSuccess) {
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleTitle.setText("发帖");

        titleFunction.setText("发送");

        edittext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return event.getKeyCode()==KeyEvent.KEYCODE_ENTER;
            }
        });
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentText.setText(s.length()+"/255");
            }
        });
    }

    @OnClick(R.id.titleFunction)
    public void onFunctionClick() {
        if (TextUtils.isEmpty(edittext.getText().toString())) {
            showToast("请输入帖子内容");
            return;
        }
        if (localImgPath.size()==0){
            mPresenter.uploadPost(null,UserInfo.getUserId(),edittext.getText().toString());
        }else {
            mPresenter.uploadImage(localImgPath);
        }
    }

    @OnClick(R.id.addImg)
    public void onAddImgClick() {

        IntentHelper.openSystemAlbumActivityForResult(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentHelper.REQUEST_CODE_GET_IMAGE && resultCode == Activity.RESULT_OK) {
            imageResult(data);
        }
    }

    private void imageResult(Intent data) {
        Uri uri = data.getData();
        Log.e("uri", uri.toString());

        String filePath = MediaUtil.getRealFilePath(this, uri);
        localImgPath.add(filePath);
//        mPresenter.uploadImg(MediaUtil.getRealFilePath(this,uri));
        addPreviewImgItem(filePath);
    }

    private void addPreviewImgItem(String img) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(getResources().getColor(R.color.borderColor));
        imageView.setLayoutParams(getImageViewLayoutParams());
        Glide.with(this).load(img).into(imageView);
        flowLayout.addView(imageView, flowLayout.getChildCount() - 1);
    }

    private FlowLayout.LayoutParams getImageViewLayoutParams() {
        int itemWidth = (Screen.getWidthPixels(getContext()) - Dimension.dp2px(getContext(), 16)) / 4;
        int margin = Dimension.dp2px(getContext(), 8);

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(itemWidth - margin * 2, itemWidth - margin * 2);
        params.setGravity(Gravity.CENTER);
        params.setMargins(margin, margin, margin, margin);

        return params;
    }
}
