package com.aytekincomez.yeniapp.Activity.Model;

public class Chat {
    private int id;
    private String gonderen;
    private String alici;
    private String aliciName;

    public String getAliciName() {
        return aliciName;
    }

    public void setAliciName(String aliciName) {
        this.aliciName = aliciName;
    }

    private String message;
    private String tarih;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    public String getAlici() {
        return alici;
    }

    public void setAlici(String alici) {
        this.alici = alici;
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
