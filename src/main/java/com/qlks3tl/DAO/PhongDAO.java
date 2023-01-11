/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlks3tl.DAO;

import com.qlks3tl.Model.NhanVien;
import java.sql.ResultSet;
import com.qlks3tl.Model.Phong;
import com.qlks3tl.utils.XJdbc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PhongDAO extends QLKSDAO<Phong, String>{

    
    String INSERT_SQL = "INSERT INTO Phong (SoPhong, GiaPhong_ngay, GiaPhong_gioi, CMND, MaLoaiPhong, TinhTrang) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE Phong SET   GiaPhong_ngay = ?, GiaPhong_gioi= ?, CMND = ?, MaLoaiPhong =?,TinhTrang=? where SoPhong =?  ";
    String DELETE_SQL = "DELETE FROM Phong WHERE SoPhong=?";
    String SELECT_ALL_SQL = "SELECT * FROM Phong";
    String SELECT_BY_ID_SQL = "SELECT * FROM Phong WHERE SoPhong LIKE ?";


    @Override
    public void insert(Phong entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getSoPhong(), entity.getGiaPhong_Ngay(), entity.getGiaPhong_Gio(), entity.getCMND(), entity.getMaLoaiPhong(),
                     entity.getTrangthai());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Phong entity) {
        try {
           XJdbc.update(UPDATE_SQL,  entity.getGiaPhong_Ngay(), entity.getGiaPhong_Gio(), entity.getCMND(), entity.getMaLoaiPhong(),
                    entity.getTrangthai(),entity.getSoPhong());
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
    public Phong selectebyID(String id) {
        List<Phong> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<Phong> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL);  }

    @Override
    public List<Phong> selectbySql(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                Phong entity = new Phong();
                entity.setSoPhong(rs.getString("SoPhong"));
                entity.setGiaPhong_Ngay(rs.getDouble("GiaPhong_ngay"));
                entity.setGiaPhong_Gio(rs.getDouble("GiaPhong_gioi"));
                entity.setCMND(rs.getString("CMND"));
                entity.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
              
                entity.setTrangthai(rs.getString("TinhTrang"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return null;
        
    }
     public List<Phong> search(String id) {
        List<Phong> list = this.selectbySql(SELECT_BY_ID_SQL, "%" + id + "%");
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
}

