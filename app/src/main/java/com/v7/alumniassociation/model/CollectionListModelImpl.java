package com.v7.alumniassociation.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.v7.alumniassociation.base.BaseCallback;
import com.v7.alumniassociation.bean.NewsBean;
import com.v7.alumniassociation.contract.CollectionListContract;
import com.v7.alumniassociation.sp.UserInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v7 on 2016/11/13.
 */

public class CollectionListModelImpl implements CollectionListContract.CollectionListModel {
    Context context;
    @Override
    public void loadCollectionList(int userId, BaseCallback callback) {
        try {
            List<NewsBean> newsBeen = new ArrayList<>();
            File file = new File(context.getExternalCacheDir().getAbsolutePath()+"/news/"+ UserInfo.getUserId());
            if (file.exists()){
                File[] files = file.listFiles();
                for (int i=0;i<files.length;i++){
                    if(files[i].isFile()){
                        BufferedReader reader = new BufferedReader(new FileReader(files[i]));
                        StringBuffer buffer = new StringBuffer();
                        while (reader.ready()){
                            buffer.append(reader.readLine());
                        }
                        NewsBean bean = new Gson().fromJson(buffer.toString(),new TypeToken<NewsBean>(){}.getType());
                        newsBeen.add(bean);
                    }
                }
                callback.result(true,null,newsBeen);
            }else {
                callback.result(false,"本地无收藏",null);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }


}
