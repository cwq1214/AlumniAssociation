package com.v7.alumniassociation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.v7.alumniassociation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/7.
 */

public class InputSingleTextDialog extends Dialog {
    @BindView(R.id.dialogTitle)
    TextView dialogTitle;
    @BindView(R.id.dialogInputText)
    EditText dialogInputText;
    @BindView(R.id.dialogCancel)
    TextView dialogCancel;
    @BindView(R.id.dialogDone)
    TextView dialogDone;

    OnDialogDoneListener onDialogDoneListener;
    boolean canNull = false;

    public InputSingleTextDialog(Context context) {
        this(context, R.style.dialog);
    }

    public InputSingleTextDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_inpuit_singel_text);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dialogCancel)
    public void onCancelClick(){
        dismiss();
    }
    @OnClick(R.id.dialogDone)
    public void onDoneClick(){
        if (canNull||dialogInputText.getText().toString().length()!=0){
            if (onDialogDoneListener!=null)
                onDialogDoneListener.onDialogClick(dialogInputText.getText().toString());
            dismiss();
        }else {
            Toast.makeText(getContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    public void setOnDialogDoneListener(OnDialogDoneListener onDialogDoneListener){
        this.onDialogDoneListener = onDialogDoneListener;
    }

    public void setDialogTitleText(String title){
        dialogTitle.setText(title);
    }

    public void setDialogInputStyle(int inputStyle){
        dialogInputText.setInputType(inputStyle);
    }

    public void setDialogHint(String hint){
        dialogInputText.setHint(hint);
    }

    public void setCanNull(boolean canNull){
        this.canNull = canNull;
    }

    public interface OnDialogDoneListener{
        void onDialogClick(String inputText);
    }
}
