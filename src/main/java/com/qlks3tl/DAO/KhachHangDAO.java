
package com.qlks3tl.DAO;

import com.qlks3tl.Model.KhachHang;
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
 * @author Admin
 */
public class KhachHangDAO extends QLKSDAO<KhachHang, String>{
        
    String INSERT_SQL = "INSERT INTO KhachHang (TenKH, CMND, Namsinh, SDT) "+ "VALUES(?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE KhachHang SET  TenKH = ?, NamSinh = ?, SDT =? WHERE CMND=?";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE CMND=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE CMND LIKE ?";

    @Override
    public void insert(KhachHang entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getTenKH(),entity.getCMND(), entity.getDOB(), entity.getSDT());
        } catch (SQLException ex) {
            Logger.getLogger(QLKSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(KhachHang entity) {
       try {
           XJdbc.update(UPDATE_SQL, entity.getTenKH(), entity.getDOB(), entity.getSDT(), entity.getCMND());
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
    public KhachHang selectebyID(String id) {
        List<KhachHang> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public List<KhachHang> selectbySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setCMND(rs.getString("CMND"));
                entity.setTenKH(rs.getString("TenKH"));
                entity.setDOB(rs.getDate("Namsinh"));
                entity.setSDT(rs.getString("SDT"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<KhachHang> search(String id) {
        List<KhachHang> list = this.selectbySql(SELECT_BY_ID_SQL, "%" + id + "%");
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
 public List<String>  layKH(){
       String sql = "select CMND from KhachHang";
       List<String> list= new ArrayList<>();
       try {
           ResultSet rs = XJdbc.query(sql);
        
           
           while(rs.next()){
              list.add(rs.getString("CMND"));
             // list.add(rs.getString("TenKH"));
               
           }
           rs.getStatement().getConnection().close();
           return list;
           
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       
   }
 
 
 
}
