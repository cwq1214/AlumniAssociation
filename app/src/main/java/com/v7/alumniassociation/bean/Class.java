package com.v7.alumniassociation.bean;

import java.util.List;

/**
 * Created by v7 on 2016/11/8.
 */

public class Class {
    //班级管理员id
    public Integer classAdminId;
    //班级id
    public Integer classId;
    //班级头像
    public String img;
    //班级用户
    public List<User> userList;
    //系
    public String series;
    //级
    public String level;
    //班级名字
    public String name;
    //管理员名字
    public String admin;
    //班级简介
    public String introduction;
}
