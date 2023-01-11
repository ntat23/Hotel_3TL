/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.DAO;

import com.qlks3tl.Model.PhieuDangKi;
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
public class PhieuDangKiDAO extends QLKSDAO<PhieuDangKi, String>{
    String INSERT_SQL = "INSERT INTO Phieu_DK (SoPhong, SoNgay,CMND ,Ngay_Checkin, Ngay_Checkout, GhiChu, MaNV,GioVao,GioRa,TinhTrangCho,TinhTrangHoaDon) "
            + "VALUES(?, ?, ?, ?, ?, ?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE Phieu_DK SET  SoNgay= ?, Ngay_Checkin =?,Ngay_Checkout =?, GhiChu=?,MaNV=?, GioVao=?, GioRa=?,TinhTrangCh.o=?,TinhTrangHoaDon=? where SoPhong =?  ";
    String DELETE_SQL = "DELETE FROM Phieu_DK WHERE MaPDK=?";
    String SELECT_ALL_SQL = "SELECT * FROM Phieu_DK";
    String SELECT_BY_ID_SQL = "SELECT * FROM Phieu_DK WHERE MaPDK = ?";
    String SELECT_BY_ID_SP = "SELECT * FROM Phieu_DK WHERE SoPhong = ?";
    String SELECT_BY_ID_SPFalseTTHD = "select * from Phieu_DK where SoPhong = ? and TinhTrangHoaDon = 0 and TinhTrangCho = 0";
    String SELECT_BY_ID_MPDKfallTTDATPHONG = "select * from Phieu_DK where MaPDK = ? and TinhTrangHoaDon = 0 and TinhTrangCho = 0";
    
    @Override
    public void insert(PhieuDangKi entity) {
          try {
            XJdbc.update(INSERT_SQL, entity.getSoPhong(), entity.getSoNgay(), entity.getTenKH(), entity.getNgayDK(), 
                    entity.getNgayTra(), entity.getGhiChu(), entity.getMaNV(), entity.getGioVao(),entity.getGioRa(),entity.getTinhTrangCho(),entity.getTinhTrangHoaDon());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
         }

    @Override
    public void update(PhieuDangKi entity) {
         try {
           XJdbc.update(UPDATE_SQL,   entity.getSoNgay(), entity.getNgayDK(),
                    entity.getNgayTra(),entity.getGhiChu(),entity.getMaNV(),entity.getGioVao(),entity.getGioRa(),
                    entity.getTinhTrangCho(),entity.getTinhTrangHoaDon(),entity.getSoPhong());
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
    public PhieuDangKi selectebyID(String id) {
     List<PhieuDangKi> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
 }
     public PhieuDangKi selectebySP(String id) {
     List<PhieuDangKi> list = this.selectbySql(SELECT_BY_ID_SP, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size()-1);
 }
      public PhieuDangKi selectbyMAPDK(int id){
         List<PhieuDangKi> list = this.selectbySql("SELECT * FROM Phieu_DK WHERE MaPDK = ?", id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
         
     }
      // update cho cap nhat chi tiet phong
    public void update22 (PhieuDangKi entity) {
         try {
           XJdbc.update("UPDATE Phieu_DK SET  SoNgay= ?, Ngay_Checkin =?,Ngay_Checkout =?,"
                   + " GhiChu=?,MaNV=?, GioVao=?, GioRa=? where SoPhong = ? and TinhTrangHoaDon = 0 and TinhTrangCho = 0",
                    entity.getSoNgay(), entity.getNgayDK(),
                    entity.getNgayTra(),entity.getGhiChu(),entity.getMaNV(),entity.getGioVao(),entity.getGioRa(),
                   entity.getSoPhong());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    public void updatepdkchothanhtoan (PhieuDangKi entity) {
         try {
           XJdbc.update("UPDATE Phieu_DK SET  SoNgay= ?, Ngay_Checkin =?,Ngay_Checkout =?, GhiChu=?,MaNV=?,"
                   + " GioVao=?, GioRa=?,TinhTrangCho=?,TinhTrangHoaDon=? where SoPhong = ? and TinhTrangHoaDon = 0 and TinhTrangCho = 0",
                    entity.getSoNgay(), entity.getNgayDK(),
                    entity.getNgayTra(),entity.getGhiChu(),entity.getMaNV(),entity.getGioVao(),entity.getGioRa(),
                    entity.getTinhTrangCho(),entity.getTinhTrangHoaDon(),entity.getSoPhong());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
      public PhieuDangKi selectebyMAPDKtrangthai0 (String id){
          List<PhieuDangKi> list = this.selectbySql(SELECT_BY_ID_SPFalseTTHD, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
 }
          
      
    
    //dung cho chitietphong thanh toan
        public PhieuDangKi selectebySP_FalseTTHoaDOn(String id) {
     List<PhieuDangKi> list = this.selectbySql(SELECT_BY_ID_SPFalseTTHD, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
 }

    @Override
    public List<PhieuDangKi> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL); }

    @Override
    public List<PhieuDangKi> selectbySql(String sql, Object... args) {
        List<PhieuDangKi> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                PhieuDangKi entity = new PhieuDangKi();
                entity.setMaPDK(rs.getInt("MaPDK"));
                entity.setSoPhong(rs.getString("SoPhong"));
                entity.setSoNgay(rs.getInt("SoNgay"));
                entity.setTenKH(rs.getString("CMND"));
                entity.setNgayDK(rs.getDate("Ngay_Checkin"));
                entity.setNgayTra(rs.getDate("Ngay_Checkout"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setGioVao(rs.getString("GioVao"));
                entity.setGioRa(rs.getString("GioRa"));
                entity.setTinhTrangCho(rs.getInt("TinhTrangCho"));
                entity.setTinhTrangHoaDon(rs.getInt("TinhTrangHoaDon"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return null; }
    
public List<Object[]> getDulieuPDk(Integer maPDK){
    List<Object[]> list = new ArrayList<>();
    try {
        ResultSet rs = null;
        try {
             String sql = "{call sp_FILLformdatPHong (?)}";
             rs = XJdbc.query(sql, maPDK);
             while (rs.next()){
                 Object[] model = {
                     rs.getInt("MaPDK"),
                     rs.getString("SoPhong"),
                     rs.getString("MaLoaiPhong"),
                     rs.getString("TenKH"),
                     rs.getDate("Ngay_Checkin"),
                      rs.getDate("Ngay_Checkout"),
                      rs.getString("TenNV")
                 };
                 list.add(model);
             }
        } finally  {
            rs.getStatement().getConnection().close();
        }
       
        
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
    return list;
    
}

 public List<Object[]> selectebySP2(String SoPhong){
        List<Object[]> list = new ArrayList();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_pdk (?)}";
             rs = XJdbc.query(sql, SoPhong);
             while (rs.next()){
                 Object[] model = {
                    rs.getInt("MaPDK"),
                    rs.getString("SoPhong"),
                    rs.getString("CMND"),
                    rs.getDate("Ngay_Checkin"),
                    rs.getDate("Ngay_Checkout"),
                    rs.getString("GioVao"),
                    rs.getString("GioRa"),
                    rs.getString("TinhTrang"),
                    rs.getInt("TinhTrangCho")
                 };
                 list.add(model);
             }
             } finally  {
            rs.getStatement().getConnection().close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 
 public List<Object[]>getDSPcho (){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_danhsachphongcho}";
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt("MaPDK"),
                        rs.getString("TenKH"),                       
                        rs.getString("SDT"),
                        rs.getString("CMND"),                       
                        rs.getString("SoPhong"),
                        rs.getString("TenLoaiPhong"),                      
                        rs.getDate("Ngay_Checkin"),
                        rs.getDate("Ngay_Checkout"),
                        rs.getString("GioVao"),
                        rs.getString("GioRa")
                        
                            
                       
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
