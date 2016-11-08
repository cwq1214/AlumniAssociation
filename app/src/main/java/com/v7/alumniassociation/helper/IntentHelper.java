package com.v7.alumniassociation.helper;

import android.content.Context;
import android.content.Intent;

import com.v7.alumniassociation.ui.activity.LoginActivity;
import com.v7.alumniassociation.ui.activity.MainActivity;
import com.v7.alumniassociation.ui.activity.PersonalInfoActivity;

/**
 * Created by v7 on 2016/11/7.
 */

public class IntentHelper {

    public static void openLoginActivity(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void openPersonalInfoActivity(Context context){
        context.startActivity(new Intent(context, PersonalInfoActivity.class));
    }

    public static void backToMainActivity(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
