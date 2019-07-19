package com.aytekincomez.yeniapp.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("post_sahibi_id")
    private int post_sahibi_id;

    @Expose
    @SerializedName("comment_sahibi_id")
    private int comment_sahibi_id;

    @Expose
    @SerializedName("comment_id")
    private int comment_id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("paths")
    private String paths;

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_sahibi_id() {
        return post_sahibi_id;
    }

    public void setPost_sahibi_id(int post_sahibi_id) {
        this.post_sahibi_id = post_sahibi_id;
    }

    public int getComment_sahibi_id() {
        return comment_sahibi_id;
    }

    public void setComment_sahibi_id(int comment_sahibi_id) {
        this.comment_sahibi_id = comment_sahibi_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String isMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
