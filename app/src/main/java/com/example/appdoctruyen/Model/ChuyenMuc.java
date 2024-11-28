package com.example.appdoctruyen.Model;

public class ChuyenMuc {
    private String tenChuyenMuc;
    private int hinhAnh;


    public ChuyenMuc(int hinhAnh, String tenChuyenMuc) {
        this.hinhAnh = hinhAnh;
        this.tenChuyenMuc = tenChuyenMuc;
    }

    public String getTenChuyenMuc() {
        return tenChuyenMuc;
    }

    public void setTenChuyenMuc(String tenChuyenMuc) {
        this.tenChuyenMuc = tenChuyenMuc;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
