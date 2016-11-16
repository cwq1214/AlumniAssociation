package com.v7.alumniassociation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.v7.alumniassociation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/8.
 */

public class SingleChooseDialog extends Dialog {
    @BindView(R.id.dialogTitle)
    TextView dialogTitle;
    @BindView(R.id.dialogRg)
    RadioGroup dialogRg;
    @BindView(R.id.dialogCancel)
    TextView dialogCancel;
    @BindView(R.id.dialogDone)
    TextView dialogDone;

    OnDoneClickListener onDoneClickListener;
    String titleText;
    List<String> names = new ArrayList<>();
    List<Boolean> checkedList = new ArrayList<>();

    String currentSelText;
    int currentSelIndex;


    public SingleChooseDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_singlechoose);
        ButterKnife.bind(this);
        buildItem();

        dialogTitle.setText(titleText);
    }

    public void addItem(String text,boolean checked){
        names.add(text);
        checkedList.add(checked);
    }
    public void setTitleText(String title){
        titleText = title;
    }

    private void buildItem(){
        dialogRg.removeAllViews();



        for (int i = 0; i < names.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(i);
            Drawable drawable= getContext().getResources().getDrawable(R.drawable.sel_check_box);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            radioButton.setButtonDrawable(getContext().getResources().getDrawable(android.R.color.transparent));
            radioButton.setCompoundDrawables(drawable,null,null,null);
            radioButton.setChecked(checkedList.get(i));
            radioButton.setTextColor(Color.BLACK);
            radioButton.setText(names.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20,0,0,0);
            radioButton.setLayoutParams(params);
            dialogRg.addView(radioButton,params);
        }

        dialogRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentSelIndex = checkedId;
                currentSelText = names.get(checkedId);
            }
        });
    }

    @OnClick(R.id.dialogCancel)
    public void onCancelClick(){
        dismiss();
    }
    @OnClick(R.id.dialogDone)
    public void onDoneClick(){
        if (onDoneClickListener!=null){
            onDoneClickListener.onDoneClick(currentSelText,currentSelIndex);
        }
        dismiss();
    }

    public void setOnDoneClickListener(OnDoneClickListener onDoneClickListener) {
        this.onDoneClickListener = onDoneClickListener;
    }

    public interface OnDoneClickListener{
        void onDoneClick(String text,int index);
    }
}
