package com.example.appdoctruyen.Model;

public class Truyen {
    private int id;
    private String tentruyen;
    private String noidung;
    private String img;
    private int id_tk;

    public Truyen(int id, String tentruyen, String noidung, String img, int id_tk) {
        this.tentruyen = tentruyen;
        this.noidung = noidung;
        this.img = img;
        this.id_tk = id_tk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId_tk() {
        return id_tk;
    }

    public void setId_tk(int id_tk) {
        this.id_tk = id_tk;
    }
}
