package com.v7.alumniassociation.bean;

/**
 * Created by v7 on 2016/11/8.
 */

public class UploadImgBean {

    public int id;
    public String img;
    public String key;
    public int userId;


    public static UploadImgBean getUploadBean(int id,String img,int type){
        UploadImgBean bean = new UploadImgBean();
        bean.img=img;
        bean.id=id;
        if (type==0) {
            bean.key = "user";
        }else if (type==1){
            bean.key = "class";
        }else if (type ==2){
            bean.key = "bar";
        }
        return bean;
    }

    public static UploadImgBean getUploadBean(int classId,int userId,String img){
        UploadImgBean uploadImgBean = new UploadImgBean();

        uploadImgBean.key="album";
        uploadImgBean.id = classId;
        uploadImgBean.userId = userId;
        uploadImgBean.img = img;

        return uploadImgBean;
    }
}
