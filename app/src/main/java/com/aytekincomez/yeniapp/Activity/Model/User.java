package com.aytekincomez.yeniapp.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    public User(String name, String paths) {
        this.name = name;
        this.paths = paths;
    }

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("biyografi")
    private String biyografi;

    @Expose
    @SerializedName("token")
    private String token;

    @Expose
    @SerializedName("image")
    private String image;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("paths")
    private String paths;

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBiyografi() {
        return biyografi;
    }

    public void setBiyografi(String biyografi) {
        this.biyografi = biyografi;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
