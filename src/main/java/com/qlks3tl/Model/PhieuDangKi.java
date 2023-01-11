/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.Model;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class PhieuDangKi {
    int maPDK;
    String  SoPhong, LoaiPhong,TenKH,GioVao,GioRa;
    Date NgayDK, NgayTra;
    String TenNV;
    int soNgay;
    String ghiChu;
    String MaNV;
    int TinhTrangCho,TinhTrangHoaDon;

    public int getTinhTrangCho() {
        return TinhTrangCho;
    }

    public void setTinhTrangCho(int TinhTrangCho) {
        this.TinhTrangCho = TinhTrangCho;
    }

    public int getTinhTrangHoaDon() {
        return TinhTrangHoaDon;
    }

    public void setTinhTrangHoaDon(int TinhTrangHoaDon) {
        this.TinhTrangHoaDon = TinhTrangHoaDon;
    }

    

    public String getGioVao() {
        return GioVao;
    }

    public void setGioVao(String GioVao) {
        this.GioVao = GioVao;
    }

    public String getGioRa() {
        return GioRa;
    }

    public void setGioRa(String GioRa) {
        this.GioRa = GioRa;
    }
    

    

    public PhieuDangKi() {
    }
public String getMaNV() {
        return MaNV;
    }

    

    public void setMaNV(String maNV) {
        this.MaNV = maNV;
    }
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public int getMaPDK() {
        return maPDK;
    }

    public void setMaPDK(int maPDK) {
        this.maPDK = maPDK;
    }

    public String getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(String SoPhong) {
        this.SoPhong = SoPhong;
    }

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public void setNgayDK(Date NgayDK) {
        this.NgayDK = NgayDK;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }
    
}
