/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlks3tl.DAO;

import com.qlks3tl.Model.LoaiPhong;
import com.qlks3tl.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LoaiPhongDAO extends QLKSDAO<LoaiPhong, String>{
     
    String INSERT_SQL = "INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong) "
            + "VALUES(?, ?)";
    String UPDATE_SQL = "UPDATE LoaiPhong SET  TenLoaiPhong = ?   WHERE MaLoaiPhong=?";
    String DELETE_SQL = "DELETE FROM LoaiPhong WHERE MaLoaiPhong=?";
    String SELECT_ALL_SQL = "SELECT * FROM LoaiPhong";
    String SELECT_BY_ID_SQL = "SELECT * FROM LoaiPhong WHERE MaLoaiPhong LIKE ?";


    @Override
    public void insert(LoaiPhong entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMaLoaiPhong(), entity.getTenLoaiPhong());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(LoaiPhong entity) {
        try {
           XJdbc.update(UPDATE_SQL, entity.getMaLoaiPhong(), entity.getTenLoaiPhong());
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
    public LoaiPhong selectebyID(String id) {
        List<LoaiPhong> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<LoaiPhong> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public List<LoaiPhong> selectbySql(String sql, Object... args) {
      List<LoaiPhong> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                LoaiPhong entity = new LoaiPhong();
                entity.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                entity.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
               
              

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return null; }


}
