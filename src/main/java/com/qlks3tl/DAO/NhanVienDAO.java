/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.DAO;

import com.qlks3tl.Model.NhanVien;
import com.qlks3tl.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class NhanVienDAO extends QLKSDAO<NhanVien, String> {
    
    String INSERT_SQL = "INSERT INTO NhanVien (MaNV, Ten, Diachi, Namsinh, Gioitinh, SDT, Chucvu, MatKhau) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NhanVien SET  Ten = ?, Diachi = ?, Namsinh = ?, Gioitinh = ?, SDT =?, Chucvu =?, MatKhau=?   WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV LIKE ?";


    @Override
    public void insert(NhanVien entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.getDchi(), entity.getDOB(), entity.isgTinh(), entity.getSdt(),
                    entity.isChucVu(), entity.getmKhau());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(NhanVien entity) {
        try {
           XJdbc.update(UPDATE_SQL, entity.getTenNV(), entity.getDchi(), entity.getDOB(), entity.isgTinh(), entity.getSdt(),
                    entity.isChucVu(), entity.getmKhau(), entity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
         try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public NhanVien selectebyID(String id) {
        List<NhanVien> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> selectbySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setTenNV(rs.getString("Ten"));
                entity.setDchi(rs.getString("Diachi"));
                entity.setDOB(rs.getDate("Namsinh"));
                entity.setgTinh(rs.getBoolean("Gioitinh"));
                entity.setSdt(rs.getString("SDT"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
               entity.setmKhau(rs.getString("MatKhau"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return null;
        
    }
     public List<NhanVien> search(String id) {
        List<NhanVien> list = this.selectbySql(SELECT_BY_ID_SQL, "%" + id + "%");
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }


    
}
