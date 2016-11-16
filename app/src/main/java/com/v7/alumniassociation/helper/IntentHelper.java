package com.v7.alumniassociation.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import com.v7.alumniassociation.ui.activity.ApplyJoinClassListActivity;
import com.v7.alumniassociation.ui.activity.BBSPostDetailActivity;
import com.v7.alumniassociation.ui.activity.BrowseImgActivity;
import com.v7.alumniassociation.ui.activity.ClassDetailActivity;
import com.v7.alumniassociation.ui.activity.CollectionListActivity;
import com.v7.alumniassociation.ui.activity.CreateClassActivity;
import com.v7.alumniassociation.ui.activity.JoinClassActivity;
import com.v7.alumniassociation.ui.activity.LoginActivity;
import com.v7.alumniassociation.ui.activity.MainActivity;
import com.v7.alumniassociation.ui.activity.ModifyPsdActivity;
import com.v7.alumniassociation.ui.activity.NewsDetailActivity;
import com.v7.alumniassociation.ui.activity.PersonalInfoActivity;
import com.v7.alumniassociation.ui.activity.SearchClassActivity;
import com.v7.alumniassociation.ui.activity.SendClassMessageActivity;
import com.v7.alumniassociation.ui.activity.SendPostActivity;
import com.v7.alumniassociation.ui.activity.UploadImgActivity;

/**
 * Created by v7 on 2016/11/7.
 */

public class IntentHelper {

    public static final int REQUEST_CODE_GET_IMAGE = 0;
    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String NEWS_ID = "NEWS_ID";
    public static final String CLASS_ID = "CLASS_ID";
    public static final String POST_ID = "POST_ID";

    public static void openSystemAlbumActivityForResult(Object activityOrFragment){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        if (activityOrFragment instanceof Activity){
            ((Activity) activityOrFragment).startActivityForResult(intent,REQUEST_CODE_GET_IMAGE);
        }else if (activityOrFragment instanceof Fragment){
            ((Fragment) activityOrFragment).startActivityForResult(intent,REQUEST_CODE_GET_IMAGE);
        }
    }

    public static void openLoginActivity(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void openPersonalInfoActivity(Context context){
        context.startActivity(new Intent(context, PersonalInfoActivity.class));
    }

    public static void backToMainActivity(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void openModifyPsdActivity(Context context){
        context.startActivity(new Intent(context, ModifyPsdActivity.class));
    }

    public static void openSendClassMessageActivity(Context context,Integer classId){
        SendClassMessageActivity.classId = classId;
        context.startActivity(new Intent(context, SendClassMessageActivity.class));
    }
    public static void openClassDetailActivity(Context context,Integer classId){
        ClassDetailActivity.classId = classId;
        context.startActivity(new Intent(context, ClassDetailActivity.class));
    }
    public static void openClassMsgBoardUploadImgActivity(Context context,Integer classId){
        UploadImgActivity.classId = classId;
        context.startActivity(new Intent(context, UploadImgActivity.class));
    }

    public static void openBrowseImageActivity(Context context,String imgUrl){
        Intent intent = new Intent(context, BrowseImgActivity.class);
        intent.putExtra(IMAGE_URL,imgUrl);
        context.startActivity(intent);
    }

    public static void openNewsDetailActivity(Context context , int newsId){
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NEWS_ID,newsId);
        context.startActivity(intent);
    }

    public static void openSendPostActivity(Context context){
        context.startActivity(new Intent(context, SendPostActivity.class));
    }

    public static void openApplyListActivity(Context context,int classId){
        Intent intent = new Intent(context, ApplyJoinClassListActivity.class);
        intent.putExtra(CLASS_ID,classId);
        context.startActivity(intent);
    }

    public static void openCreateClassActivity(Context context){
        context.startActivity(new Intent(context, CreateClassActivity.class));
    }
    public static void openSearchClassActivity(Context context){
        context.startActivity(new Intent(context, SearchClassActivity.class));
    }

    public static void openJoinClassActivity(Context context,int classId){
        Intent intent = new Intent(context, JoinClassActivity.class);
        intent.putExtra(CLASS_ID,classId);
        context.startActivity(intent);

    }

    public static void openCollectionListActivity(Context context){
        context.startActivity(new Intent(context, CollectionListActivity.class));
    }

    public static void openBBSDetailActivity(Context context,int postId){
        Intent intent = new Intent(context, BBSPostDetailActivity.class);
        intent.putExtra(POST_ID,postId);
        context.startActivity(intent);
    }
}
