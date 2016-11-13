package com.v7.alumniassociation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.util.Dimension;
import com.v7.alumniassociation.util.Screen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/9.
 */

public class ClassMemberOptionalDialog extends Dialog {
    @BindView(R.id.dialogTitle)
    TextView dialogTitle;
    @BindView(R.id.dialogChangeAdmin)
    TextView dialogChangeAdmin;
    @BindView(R.id.removeToClass)
    TextView removeToClass;
    @BindView(R.id.cancel)
    TextView cancel;


    OnItemClickListener onItemClickListener;
    String titleText;

    public ClassMemberOptionalDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_class_member_optional);
        ButterKnife.bind(this);

        getWindow().setLayout((int) (Screen.getWidthPixels(getContext())*0.65), ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogTitle.setText(titleText);
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    @OnClick({R.id.dialogChangeAdmin, R.id.removeToClass, R.id.cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialogChangeAdmin:
                if (onItemClickListener!=null)
                    onItemClickListener.onChangeAdminClick();
                break;
            case R.id.removeToClass:
                if (onItemClickListener!=null)
                    onItemClickListener.onRemoveToClassClick();
                break;
            case R.id.cancel:
                break;
        }
        dismiss();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onChangeAdminClick();

        void onRemoveToClassClick();
    }
}
