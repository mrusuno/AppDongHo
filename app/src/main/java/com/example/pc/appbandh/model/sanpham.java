package com.example.pc.appbandh.model;

import java.io.Serializable;

/**
 * Created by PC on 10/21/2017.
 */

public class sanpham implements Serializable {
    public int Id;
    public int Idlsp;
    public String Tensanpham;
    public int Giasanpham;
    public String Mota;
    public String Hinhanhsanpham;

    public sanpham(int id, int idlsp, String tensanpham, int giasanpham, String mota, String hinhanhsanpham) {
        Id = id;
        Idlsp = idlsp;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Mota = mota;
        Hinhanhsanpham = hinhanhsanpham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdlsp() {
        return Idlsp;
    }

    public void setIdlsp(int idlsp) {
        Idlsp = idlsp;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }
}
