package com.aytekincomez.yeniapp.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    public Comment(int id, int user_id, int post_id, String comment, int begeniDurum, String tarih) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment = comment;
        this.begeniDurum = begeniDurum;
        this.tarih = tarih;
    }

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("user_id")
    private int user_id;

    @Expose
    @SerializedName("name")
    private String name;


    @Expose
    @SerializedName("post_id")
    private int post_id;

    @Expose
    @SerializedName("comment")
    private String comment;

    @Expose
    @SerializedName("begeniDurum")
    private int begeniDurum;

    @Expose
    @SerializedName("tarih")
    private String tarih;

    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBegeniDurum() {
        return begeniDurum;
    }

    public void setBegeniDurum(int begeniDurum) {
        this.begeniDurum = begeniDurum;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
