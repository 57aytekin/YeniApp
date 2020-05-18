package com.aytekincomez.yeniapp.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageList {

    public MessageList(int id, int gonderen_id, int alici_id, String alici_name, String alici_photo, String message, String tarih, String name, String paths) {
        this.id = id;
        this.gonderen_id = gonderen_id;
        this.alici_id = alici_id;
        this.alici_name = alici_name;
        this.alici_photo = alici_photo;
        this.message = message;
        this.tarih = tarih;
        this.name = name;
        this.paths = paths;
    }

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("gonderen_id")
    private int gonderen_id;

    @Expose
    @SerializedName("alici_id")
    private int alici_id;

    @Expose
    @SerializedName("alici_name")
    private String alici_name;

    @Expose
    @SerializedName("alici_photo")
    private String alici_photo;

    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("tarih")
    private String tarih;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("paths")
    private String paths;

    @Expose
    @SerializedName("success") private boolean success;

    public String getAlici_name() {
        return alici_name;
    }

    public void setAlici_name(String alici_name) {
        this.alici_name = alici_name;
    }

    public String getAlici_photo() {
        return alici_photo;
    }

    public void setAlici_photo(String alici_photo) {
        this.alici_photo = alici_photo;
    }




    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGonderen_id() {
        return gonderen_id;
    }

    public void setGonderen_id(int gonderen_id) {
        this.gonderen_id = gonderen_id;
    }

    public int getAlici_id() {
        return alici_id;
    }

    public void setAlici_id(int alici_id) {
        this.alici_id = alici_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
