package com.example.pc.appbandh.model;

/**
 * Created by PC on 10/21/2017.
 */

public class loaisanpham {
    public int Id;
    public  String Tenloaisp;
    public String Hinhanhloaisp;

    public loaisanpham(int id, String teenloaisp, String hinhanhloaisp) {
        Id = id;
        Tenloaisp = teenloaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }
}
