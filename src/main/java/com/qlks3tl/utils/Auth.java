/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.utils;

import com.qlks3tl.Model.NhanVien;


/**
 *
 * @author HAN-PC
 */
public class Auth {
    public static NhanVien user = null;
    public static void clear() {
        Auth.user = null;
    }
    public static boolean isLogin() {
        return Auth.user != null;
    }
    // 1 la quan ly --  0 la nv
    public static boolean isManager() {
        return Auth.isLogin() && user.isChucVu(); 
    }
}
