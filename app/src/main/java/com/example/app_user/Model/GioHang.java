package com.example.app_user.Model;

public class GioHang {
    private String tenSach;
    private String linkAnh;
    private int gia;
    private int cbo ;
    private int soluong;
    private int dongia;
    private int idSach;

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public GioHang(String tenSach, String linkAnh, int gia, int cbo, int soluong) {
        this.tenSach = tenSach;
        this.linkAnh = linkAnh;
        this.gia = gia;
        this.cbo = cbo;
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public GioHang() {

    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getCbo() {
        return cbo;
    }

    public void setCbo(int cbo) {
        this.cbo = cbo;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
