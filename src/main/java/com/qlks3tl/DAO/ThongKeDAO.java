/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.DAO;

import com.qlks3tl.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ThongKeDAO {
    public List<Object[]>getDoanhThuNam(Integer maHD){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThuNam (?)}";
                rs = XJdbc.query(sql, maHD);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("TenKH"),
                        rs.getString("CMND"),
                        rs.getString("SDT"),
                        rs.getString("MaLoaiPhong"),
                        rs.getString("SoPhong"),
                        rs.getDouble("Tong"),
                       
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<Object[]>getDoanhThuThang(Object ...args){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThuThangNam (?,?)}";
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                   Object[] model = {
                        rs.getString("TenKH"),
                        rs.getString("CMND"),
                        rs.getString("SDT"),
                        rs.getString("MaLoaiPhong"),
                        rs.getString("SoPhong"),
                        rs.getDouble("Tong"),
                       
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
}
