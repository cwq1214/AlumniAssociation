package com.v7.alumniassociation.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.v7.alumniassociation.R;
import com.v7.alumniassociation.adapter.FragmentViewPagerAdapter;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.sp.UserInfo;
import com.v7.alumniassociation.ui.fragment.BBSFragment;
import com.v7.alumniassociation.ui.fragment.ClassFragment;
import com.v7.alumniassociation.ui.fragment.HadClassFragment;
import com.v7.alumniassociation.ui.fragment.HomeFragment;
import com.v7.alumniassociation.ui.fragment.PersonalFragment;
import com.v7.alumniassociation.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragments;
    @BindView(R.id.btn_main_home)
    RadioButton btnMainHome;
    @BindView(R.id.btn_main_bbs)
    RadioButton btnMainBbs;
    @BindView(R.id.btn_main_class)
    RadioButton btnMainClass;
    @BindView(R.id.btn_main_personal)
    RadioButton btnMainPersonal;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        initViewPager();
        setupRgChangeListener();
    }

    private void initViewPager(){
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(),fragments));
    }

    private void setupRgChangeListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_main_bbs:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.btn_main_class:
                        if (UserInfo.getUserId()==null){
                            IntentHelper.openLoginActivity(getContext());
                            btnMainHome.setChecked(true);
                            return;
                        }
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.btn_main_home:

//                        if (UserInfo.hadClass()&&!(fragments.get(2) instanceof HadClassFragment)){
//                            fragments.remove(2);
//                            fragments.add(new HadClassFragment());
//                        }else if (!UserInfo.hadClass()&&!(fragments.get(2) instanceof NotHadClassFragment)){
//                            fragments.remove(2);
//                            fragments.add(new NotHadClassFragment());
//                        }
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.btn_main_personal:
                        if (UserInfo.getUserId()==null){
                            IntentHelper.openLoginActivity(getContext());
                            btnMainHome.setChecked(true);
                            return;
                        }
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    public void initFragments() {
        fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new BBSFragment());
        fragments.add(new ClassFragment());
        fragments.add(new PersonalFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (UserInfo.getUserId()==null&&btnMainPersonal.isChecked()){
            btnMainHome.setChecked(true);
        }
    }

    public Context getContext(){
        return this;
    }
}
