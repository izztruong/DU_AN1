package com.example.app_user.Model;

import java.io.Serializable;

public class Sach implements Serializable {
    int idSach;
    int idNxb;
    String tenSach;
    int gia;

    String moTa;
    String linkAnh;

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public int getIdNxb() {
        return idNxb;
    }

    public void setIdNxb(int idNxb) {
        this.idNxb = idNxb;
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
}
