package com.example.app_user.Model;

public class tbGioHang {
    private int id;
    private int idSach;
    private String tenSach;
    private int gia;
    private String linkAnh;
    private int soLuong;

    public tbGioHang() {
    }

    public tbGioHang(int id, int idSach, String tenSach, int gia, String linkAnh, int soLuong) {
        this.id = id;
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.gia = gia;
        this.linkAnh = linkAnh;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
