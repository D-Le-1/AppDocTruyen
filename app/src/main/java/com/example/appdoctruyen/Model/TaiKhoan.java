package com.example.appdoctruyen.Model;

public class TaiKhoan {
    //Các biến
    private int idtaikhoan;
    private String tentaikhoan;
    private String matkhau;
    private String email;
    private int phanquyen;

    //Hàm khởi tạo
    public TaiKhoan(String tentaikhoan, String matkhau, String email, int phanquyen) {
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.email = email;
        this.phanquyen = phanquyen;
    }

    public TaiKhoan(String tentaikhoan, String email) {
        this.tentaikhoan = tentaikhoan;
        this.email = email;
    }
    //getter và setter
    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(int phanquyen) {
        this.phanquyen = phanquyen;
    }
}
