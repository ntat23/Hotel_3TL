/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Acer
 */
public class formatt {
   
    public static String formatMoney(double data){
        Locale localeVN = new Locale("vi","VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        return vn.format(data) + "00 VND";
    }
    
}
