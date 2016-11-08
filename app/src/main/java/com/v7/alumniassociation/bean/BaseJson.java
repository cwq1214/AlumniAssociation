package com.v7.alumniassociation.bean;

/**
 * Created by v7 on 2016/11/7.
 */

public class BaseJson<T>{
    public String forUser;
    public boolean ret;
    public int code;
    public T data;

    @Override
    public String toString() {
        return "BaseJson{" +
                "forUser='" + forUser + '\'' +
                ", ret=" + ret +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}

