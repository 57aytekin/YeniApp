package com.aytekincomez.yeniapp.Activity.Model;

public class Post {
    private int post_id;
    private int user_id;
    private String post_text;
    private String tarih;

    public Post() {
    }

    public Post(int post_id, int user_id, String post_text, String tarih) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.post_text = post_text;
        this.tarih = tarih;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
