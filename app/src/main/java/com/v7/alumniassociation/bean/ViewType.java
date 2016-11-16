package com.v7.alumniassociation.bean;

/**
 * Created by v7 on 2016/11/13.
 */

public class ViewType<T> {
    public static final int POST_HEAD=0;
    public static final int POST_COMMENT=1;


    public int type;
    public T data;
}
