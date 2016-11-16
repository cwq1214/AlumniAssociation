package com.v7.alumniassociation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.base.BaseActivity;
import com.v7.alumniassociation.contract.SendClassMessageContract;
import com.v7.alumniassociation.presenter.SendClassMessagePresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/8.
 */

public class SendClassMessageActivity extends BaseActivity<SendClassMessageContract.SendClassMessagePresenter> implements SendClassMessageContract.SendClassMessageView {
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

    public static Integer classId;

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected Integer getLayoutResource() {
        return R.layout.activity_sendclassmessage;
    }

    @Override
    protected SendClassMessageContract.SendClassMessagePresenter getPresenterImpl() {
        return new SendClassMessagePresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleFunction.setText("发送");
        titleTitle.setText("留言");

        setListener();
    }

    private void setListener(){
        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                    return true;
                }
                return false;
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
    public void onFunctionClick(){
        mPresenter.sendClassMessage(edittext.getText().toString(),classId);
    }

    @Override
    public void onSendClassMessageCallback(boolean isSuccess) {
        if (isSuccess){
            finish();
        }
    }
}
