/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlks3tl.utils;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Acer
 */
public class vaildatecheck {
    public static void vaildatecheckEmpty(JTextField field , StringBuilder sb, String errorMessage ){
        if (field.getText().equals("")){
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.RED);
            field.requestFocus();
        }
        }
         public static void vaildatecheckEmpty(JPasswordField field , StringBuilder sb, String errorMessage ){
             String password = new String(field.getPassword());
        if (password.equals("")){
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.RED);
            field.requestFocus();
        }
    }
    
}
