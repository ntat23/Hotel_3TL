/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mypackage.UI;

import com.qlks3tl.DAO.PhieuDangKiDAO;
import com.qlks3tl.DAO.PhongDAO;
import com.qlks3tl.Model.HoaDon;
import com.qlks3tl.Model.PhieuDangKi;
import com.qlks3tl.Model.Phong;
import com.qlks3tl.utils.Auth;
import com.qlks3tl.utils.MsgBox;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Acer
 */
public class frm_CapNhat extends javax.swing.JFrame {

    /**
     * Creates new form DoiMK
     */
    public frm_CapNhat() {
        initComponents();
        init();
        setLocationRelativeTo(null);
        
    }
    public void init(){
        this.updateStatus();
        designTable();
       if (!Auth.isManager()) {
            
            btn_CNGP_capnhat.setEnabled(false);
        } else{
           btn_CNGP_capnhat.setEnabled(true);
           
       }
       // this.fillTable();

    }
     void designTable() {
        tbl_CNGP_DsdatPhong.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        tbl_CNGP_DsdatPhong.getTableHeader().setForeground(Color.BLACK);
     }
   
    PhongDAO dao = new PhongDAO();
    PhieuDangKiDAO daopdk = new  PhieuDangKiDAO();
    PhieuDangKi pdka = new PhieuDangKi();
    HoaDon hd = new HoaDon();
    List<Phong> list = dao.selectAll();
    int row = -1;
    
      
    
    void fillTable(PhieuDangKi pdka){
        DefaultTableModel model = (DefaultTableModel) tbl_CNGP_DsdatPhong.getModel();
        model.setRowCount(0);
        String SoPhong = txt_CNGP_SoPhong1.getText();
        System.out.println(SoPhong);
        try{
            List<Object[]> list = daopdk.selectebySP2(SoPhong);// Lấy tất cả dữ liệu từ SQL
            for( Object[] pdk: list){
                Object[] row ={
                  /* pdk.getMaPDK(),pdk.getSoPhong(),pdk.getSoNgay(),pdk.getTenKH(),pdk.getNgayDK(),pdk.getNgayTra()
                    ,pdk.getGhiChu(),pdk.getMaNV(),
                    pdk.getGioVao(),pdk.getGioRa()*/
                    (int)pdk[0],pdk[1],pdk[2],(Date)pdk[3],(Date)pdk[4],pdk[5],pdk[6],pdk[7],(int)pdk[8]
                };
                model.addRow(row);
            }
           
          
        }
        catch(Exception e){
            MsgBox.alert(this,"Lỗi truy vấn");
        }
    }
    
       
//    public Phong edit(){
//        Phong p = new Phong();
//        if (row != -1) {
//            String SoPhong = (String) tbl_CNGP_DsdatPhong.getValueAt(this.row, 1);
//            tbl_CNGP_DsdatPhong.setRowSelectionInterval(row, row);
//            p = dao.selectebyID(SoPhong);
//           
//          
//        }
//       return p;
//    }
    
     public PhieuDangKi edit(){
        PhieuDangKi pkdedit = new PhieuDangKi();
        if (row != -1) {
            int MaPDK = (int) tbl_CNGP_DsdatPhong.getValueAt(this.row,0 );
            tbl_CNGP_DsdatPhong.setRowSelectionInterval(row, row);
            pkdedit = daopdk.selectbyMAPDK(MaPDK);
           
          
        }
       return pkdedit;
    }
   
    //show thông tin ngày ra, ngày vào của phòng đang sử dụng và đặt trước
     void setForm(PhieuDangKi pdk){
       
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date date = (daopdk.selectebyID(Integer.toString(pdk.getMaPDK()))).getNgayDK();
        Date date1 = (daopdk.selectebyID(Integer.toString(pdk.getMaPDK()))).getNgayTra();
	String dateFormat = formatter.format(date);
        String dateFormat1 = formatter.format(date1);
        
      
       
        txt_CNGP_Ngayvao.setText(dateFormat);
        txt_CNGP_Ngayra.setText(dateFormat1);
    
     
   
    }
    
    
     void setForm(Phong p){
       
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date date = daopdk.selectebySP(p.getSoPhong()).getNgayDK();
        Date date1 = daopdk.selectebySP(p.getSoPhong()).getNgayTra();
	String dateFormat = formatter.format(date);
        String dateFormat1 = formatter.format(date1);
        
        txt_CNGP_SoPhong1.setText(p.getSoPhong());
        txt_CNGP_MLPhong.setText(p.getMaLoaiPhong());
        txt_CNGP_GiaPNgay.setText(String.valueOf(p.getGiaPhong_Ngay()));
        txt_CNGP_GiaPGio.setText(String.valueOf(p.getGiaPhong_Gio()));
        txt_CNGP_Trangthai.setText(p.getTrangthai());
        txt_CNGP_Ngayvao.setText(dateFormat);
        txt_CNGP_Ngayra.setText(dateFormat1);
    
    }
    
    Phong getForm(){
        Phong p = new Phong();
        PhieuDangKi pdk = new PhieuDangKi();
        p.setGiaPhong_Ngay(Double.valueOf(txt_CNGP_GiaPNgay.getText()));
        p.setGiaPhong_Gio(Double.valueOf(txt_CNGP_GiaPGio.getText()));   
       // pdk.setNgayDK((txt_CNGP_Ngayvao.getText()));
     //   pdk.setNgayTra(Int.valueOf(txt_CNGP_Ngayra.getText()));

        return p;
    }
    
    void update(){
        if(kiemtra()){
            Phong p = getForm();
            
            try{
                dao.update(p);
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }
    
    void updateStatus(){
        
        //Trạng thái form
        
        
        txt_CNGP_SoPhong1.setEnabled(false);
        txt_CNGP_MLPhong.setEnabled(false);
        txt_CNGP_Trangthai.setEnabled(false);
        txt_CNGP_GiaPNgay.setEnabled(false);
        txt_CNGP_GiaPGio.setEnabled(false);
        txt_CNGP_Ngayvao.setEnabled(false);
        txt_CNGP_Ngayra.setEnabled(false);
            
       
    }

     void updateStatus1(){
        
        //Trạng thái form
        
        
        txt_CNGP_SoPhong1.setEnabled(false);
        txt_CNGP_MLPhong.setEnabled(false);
        txt_CNGP_Trangthai.setEnabled(false);
        txt_CNGP_GiaPNgay.setEnabled(true);
        txt_CNGP_GiaPGio.setEnabled(true);
        txt_CNGP_Ngayvao.setEnabled(true);
        txt_CNGP_Ngayra.setEnabled(true);
            
       
    }
    
    boolean kiemtra(){
        double gia= 0;
        double gia1= 0;
        if ((txt_CNGP_GiaPNgay.getText()).equals("") || (txt_CNGP_GiaPGio.getText()).equals("")) {
            MsgBox.alert(this, "Vui lòng cập nhật lại giá Phòng!!!");
            return false;
        } else{
                gia = Double.parseDouble(txt_CNGP_GiaPNgay.getText());
                gia1 = Double.parseDouble(txt_CNGP_GiaPGio.getText());
                if (gia <= 0 || gia1 <= 0) {
                   MsgBox.alert(this, "Vui lòng nhập Chính xác Giá Phòng!!!");
                   return false;
                }
        }
        
        return true;
    }
   

  
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_CNGP_GiaPNgay = new javax.swing.JTextField();
        txt_CNGP_MLPhong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_CNGP_GiaPGio = new javax.swing.JTextField();
        txt_CNGP_Trangthai = new javax.swing.JTextField();
        txt_CNGP_SoPhong1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_CNGP_Ngayra = new javax.swing.JTextField();
        txt_CNGP_Ngayvao = new javax.swing.JTextField();
        btn_CNGP_capnhat = new javax.swing.JButton();
        btn_CN_thoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_CNGP_DsdatPhong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(99, 99, 198));
        jLabel1.setText("Cập Nhật");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jLabel1);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(99, 99, 198));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Giá Phòng - Ngày");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        txt_CNGP_GiaPNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CNGP_GiaPNgayActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(99, 99, 198));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mã Loại Phòng");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(99, 99, 198));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Số Phòng");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(99, 99, 198));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Giá Phòng - Giờ");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(99, 99, 198));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Trạng Thái");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        txt_CNGP_GiaPGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CNGP_GiaPGioActionPerformed(evt);
            }
        });

        txt_CNGP_Trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CNGP_TrangthaiActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(99, 99, 198));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ngày Ra");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(99, 99, 198));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Ngày Vào");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 255)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_CNGP_GiaPNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_GiaPGio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_CNGP_MLPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_SoPhong1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_CNGP_Trangthai)
                    .addComponent(txt_CNGP_Ngayra)
                    .addComponent(txt_CNGP_Ngayvao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_SoPhong1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_Ngayvao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_MLPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_Ngayra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_CNGP_GiaPNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CNGP_Trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_CNGP_GiaPGio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        btn_CNGP_capnhat.setBackground(new java.awt.Color(99, 99, 198));
        btn_CNGP_capnhat.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btn_CNGP_capnhat.setForeground(new java.awt.Color(255, 255, 255));
        btn_CNGP_capnhat.setText("Xác nhận");
        btn_CNGP_capnhat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        btn_CNGP_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CNGP_capnhatActionPerformed(evt);
            }
        });

        btn_CN_thoat.setBackground(new java.awt.Color(99, 99, 198));
        btn_CN_thoat.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btn_CN_thoat.setForeground(new java.awt.Color(255, 255, 255));
        btn_CN_thoat.setText("Thoát");
        btn_CN_thoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        btn_CN_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CN_thoatActionPerformed(evt);
            }
        });

        tbl_CNGP_DsdatPhong.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(168, 222, 247), 4, true));
        tbl_CNGP_DsdatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã PDK", "Số Phòng", "CMND", "Ngày vào", "Ngày ra", "Giờ vào", "Giờ ra", "Trạng Thái", "Tình Trạng Chờ"
            }
        ));
        tbl_CNGP_DsdatPhong.setGridColor(new java.awt.Color(150, 196, 200));
        tbl_CNGP_DsdatPhong.setRowHeight(27);
        tbl_CNGP_DsdatPhong.setRowMargin(3);
        tbl_CNGP_DsdatPhong.setSelectionBackground(new java.awt.Color(150, 196, 200));
        tbl_CNGP_DsdatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CNGP_DsdatPhongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_CNGP_DsdatPhong);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_CNGP_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_CN_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CNGP_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CN_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_CNGP_GiaPNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CNGP_GiaPNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CNGP_GiaPNgayActionPerformed

    private void txt_CNGP_GiaPGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CNGP_GiaPGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CNGP_GiaPGioActionPerformed

    private void txt_CNGP_TrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CNGP_TrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CNGP_TrangthaiActionPerformed

    private void btn_CN_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CN_thoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_CN_thoatActionPerformed

    private void btn_CNGP_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CNGP_capnhatActionPerformed
        // TODO add your handling code here:
        
        update();
    }//GEN-LAST:event_btn_CNGP_capnhatActionPerformed

    private void tbl_CNGP_DsdatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CNGP_DsdatPhongMouseClicked
        
        if(evt.getClickCount() == 1){
        
            Phong p = new Phong();
            PhieuDangKi pdk = new PhieuDangKi();
            this.row = tbl_CNGP_DsdatPhong.getSelectedRow();
            //p = this.edit();  
            DefaultTableModel model = (DefaultTableModel) tbl_CNGP_DsdatPhong.getModel();
            int value = (int) model.getValueAt(row, 8 );
            
            if(value ==  1){
                pdk = this.edit();
                this.setForm(p);
                this.updateStatus1();
             
            }else{
                this.updateStatus();
            }
        }
    }//GEN-LAST:event_tbl_CNGP_DsdatPhongMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_CapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_CapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_CapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_CapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_CapNhat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CNGP_capnhat;
    private javax.swing.JButton btn_CN_thoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_CNGP_DsdatPhong;
    private javax.swing.JTextField txt_CNGP_GiaPGio;
    private javax.swing.JTextField txt_CNGP_GiaPNgay;
    private javax.swing.JTextField txt_CNGP_MLPhong;
    private javax.swing.JTextField txt_CNGP_Ngayra;
    private javax.swing.JTextField txt_CNGP_Ngayvao;
    private javax.swing.JTextField txt_CNGP_SoPhong1;
    private javax.swing.JTextField txt_CNGP_Trangthai;
    // End of variables declaration//GEN-END:variables

  
}
