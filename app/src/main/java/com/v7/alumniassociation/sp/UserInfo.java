package com.v7.alumniassociation.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;
import com.orhanobut.hawk.Serializer;
import com.orhanobut.hawk.Storage;
import com.v7.alumniassociation.BuildConfig;

/**
 * Created by v7 on 2016/11/7.
 */

public class UserInfo {

    private static String k_userId = "userId";
    private static String k_no = "no";
    private static String k_img = "img";
    private static String k_name = "name";
    private static String k_hadClass = "hclass";
    private static String k_classIdForAdmin = "classId";

    private static String spName = "user.db";


    public static void init(Context context){
        Hawk.init(context).setLogInterceptor(new LogInterceptor() {
            @Override
            public void onLog(String message) {
                if (BuildConfig.DEBUG)
                Log.d("Hawk",message);
            }
        }).build();
    }

    public static Integer getClassId(){
        return Hawk.get(k_classIdForAdmin,null);
    }
    public static void setClassId(Integer id){
        Hawk.put(k_classIdForAdmin,id);
    }

    public static void removeAll(){
        Hawk.deleteAll();
    }

    public static boolean hadClass(){
        return Hawk.get(k_hadClass);
    }

    public static void setHadClass(boolean hadClass){
        Hawk.put(k_hadClass,hadClass);
    }

    public static Integer getUserId(){
        return Hawk.get(k_userId);
    }
    public static void setUserId(int userId){
        Hawk.put(k_userId,userId);
    }

    public static void setUserName(String name){
        Hawk.put(k_name,name);
    }
    public static String getUserName(){
        return Hawk.get(k_name);
    }

    public static void setNo(String no){
        Hawk.put(k_no,no);
    }

    public static String getNo(){
        return Hawk.get(k_no);
    }

    public static String getImg(){
        return Hawk.get(k_img);
    }

    public static void setImg(String img){
        Hawk.put(k_img,img);
    }

}
