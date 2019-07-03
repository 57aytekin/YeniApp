package com.aytekincomez.yeniapp.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @Expose
    @SerializedName("id")
    private int post_id;
    @Expose
    @SerializedName("useri_id")
    private int user_id;
    @Expose
    @SerializedName("name")
    private String user_name;
    @Expose
    @SerializedName("paths")
    private String image;
    @Expose
    @SerializedName("share_post")
    private String post_text;
    @Expose
    @SerializedName("like_count")
    private int like_count;
    @Expose
    @SerializedName("comment_count")
    private int comment_count;
    @Expose
    @SerializedName("tarih")
    private String tarih;

    public int getPost_id() {
        return post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPost_text() {
        return post_text;
    }

    public int getLike_count() {
        return like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public String getTarih() {
        return tarih;
    }

    public String getImage() {
        return image;
    }
}
