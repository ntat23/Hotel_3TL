/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *

 */
public class XJdbc {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=QLKS_3TL_Final;;encrypt=true;trustServerCertificate=true;";
    static String username="sa";
    static String password="123";
//    static String user = "sa";
//    static String pass = "123";
    static{
        try {
            Class.forName(driver);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{ //Hàm này để phục vụ cho 3 hàm duoi
        Connection conn = DriverManager.getConnection(dburl, username, password);
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        }else{
            stmt = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    
    public static int update(String sql, Object...args) throws SQLException{
        try{
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            }finally{
                stmt.getConnection().close();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object...args) throws SQLException{
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void ExecuteTruyVan(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
