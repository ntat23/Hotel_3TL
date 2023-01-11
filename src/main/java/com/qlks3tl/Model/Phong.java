/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlks3tl.Model;

/**
 *
 * @author Admin
 */
public class Phong {
    String SoPhong;
    Double GiaPhong_Ngay;
    Double GiaPhong_Gio;
    String CMND;
    String MaLoaiPhong;
    
    String Trangthai;

    public Phong() {
    }

    public String getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(String SoPhong) {
        this.SoPhong = SoPhong;
    }

    public Double getGiaPhong_Ngay() {
        return GiaPhong_Ngay;
    }

    public void setGiaPhong_Ngay(Double GiaPhong_Ngay) {
        this.GiaPhong_Ngay = GiaPhong_Ngay;
    }

    public Double getGiaPhong_Gio() {
        return GiaPhong_Gio;
    }

    public void setGiaPhong_Gio(Double GiaPhong_Gio) {
        this.GiaPhong_Gio = GiaPhong_Gio;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

   

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String Trangthai) {
        this.Trangthai = Trangthai;
    }

    
}
