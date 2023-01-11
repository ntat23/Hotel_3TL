/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlks3tl.DAO;

import com.qlks3tl.Model.HoaDon;
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
public class HoaDonDAO extends QLKSDAO<HoaDon, String>{
    String INSERT_SQL = "set identity_insert HoaDon on;"+"INSERT INTO HoaDon (MaHD, MaNV, MaPDK, CMND, SoPhong, NgayTao, TongTien, SoNgay) "+ "VALUES(?, ?, ?, ?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET  TenKH = ?, NamSinh = ?, SDT =? WHERE CMND=?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHD=?";
    String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHD LIKE ?";
    
    @Override
    public void insert(HoaDon entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMaHD(), entity.getMaNV(),entity.getMaPDK(), entity.getCMND(), entity.getSoPhong(), entity.getNgayTao(),
                    entity.getTongTien(),entity.getSoNgay());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(HoaDon entity) {
       try {
           XJdbc.update(UPDATE_SQL,entity.getMaHD(), entity.getMaNV(),entity.getMaPDK(), entity.getCMND(), entity.getSoPhong(), entity.getGhiChu(), entity.getNgayTao(),
                    entity.getTongTien());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public HoaDon selectebyID(String id) {
        List<HoaDon> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public List<HoaDon> selectbySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaPDK(rs.getInt("MaPDK"));
                entity.setCMND(rs.getString("CMND"));
                entity.setSoPhong(rs.getString("SoPhong"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setNgayTao(rs.getDate("NgayTao"));
               entity.setTongTien(rs.getDouble("TongTien"));
               entity.setSoNgay(rs.getInt("SoNgay"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<HoaDon> search(String id) {
        List<HoaDon> list = this.selectbySql(SELECT_BY_ID_SQL, "%" + id + "%");
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
    
   public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(Ngay_Checkin) Year FROM Phieu_DK ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Integer> selectMonth() {
        String sql = "SELECT DISTINCT Month(Ngay_Checkin) Month FROM Phieu_DK ORDER BY Month DESC";
        List<Integer> lists = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                lists.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return lists;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    public List<Object[]>getMaKHvaDaThanhtoan(String CMND){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_showkhachhangvaphongdadat (?)}";
                rs = XJdbc.query(sql, CMND);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("TenKH"),
                        rs.getString("CMND"),
                        rs.getString("SDT"),
                        rs.getString("MaLoaiPhong"),
                        rs.getString("SoPhong"),
                        rs.getDouble("TongTien"),
                        rs.getDate("Ngay_Checkin"),
                        rs.getDate("Ngay_Checkout"),
                        rs.getInt("SoNgay"),
                        
                            
                       
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