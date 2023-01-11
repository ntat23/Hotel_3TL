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
public class NhanVien {
    String MaNV, tenNV, Dchi;
    Date DOB;
    boolean gTinh;
    String sdt;
    boolean chucVu;
    String mKhau;

    public NhanVien() {
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDchi() {
        return Dchi;
    }

    public void setDchi(String Dchi) {
        this.Dchi = Dchi;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public boolean isgTinh() {
        return gTinh;
    }

    public void setgTinh(boolean gTinh) {
        this.gTinh = gTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public String getmKhau() {
        return mKhau;
    }

    public void setmKhau(String mKhau) {
        this.mKhau = mKhau;
    }
    
            
            
    
}
