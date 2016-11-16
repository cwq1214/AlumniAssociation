package com.v7.alumniassociation.bean;

import java.util.List;

/**
 * Created by v7 on 2016/11/11.
 */

public class PostDetailBean {
    public Bar bar;
    public List<Comment> comment;
    public class Bar{
        public String b_content;
        public String createdTime;
        public String b_imgs;
        public int id;
        public int userId;
        public String b_state;
        public String name;
        public String img;
    }
    public class Comment{
        public int commentId;
        public String createdTime;
        public int id;
        public int barId;
        public String ub_state;
        public int userId;
        public String content;
        public String name;
        public String img;
    }
}

