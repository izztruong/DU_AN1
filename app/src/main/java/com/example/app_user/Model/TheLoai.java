package com.example.app_user.Model;

public class TheLoai {
    private int id;
    private int idTheLoai;
    private String tenTheLoai;

    public TheLoai(int id, int idTheLoai, String tenTheLoai) {
        this.id = id;
        this.idTheLoai = idTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoai() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
