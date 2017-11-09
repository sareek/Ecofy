
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sareek
 */
public class WelcomePokhara extends javax.swing.JFrame {
    
    Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;

    /**
     * Creates new form WelcomePokhara
     */
    public WelcomePokhara() {
        initComponents();
    }
    
     public ArrayList<ItemsM2> BindTable(){
        
        ArrayList<ItemsM2> itemsList= new ArrayList<ItemsM2>();
         conn=MySQLConnect.ConnectDB();
        try {
            String query1="Select id,place, average, complain, image,date from pokhara";
          pst = conn.prepareStatement(query1);
          
            ResultSet rs=pst.executeQuery(query1);
            ItemsM2 items;
            while(rs.next()){
                items=new ItemsM2(rs.getInt("id"),rs.getString("place"),rs.getFloat("average"),rs.getString("complain"),rs.getString("image"),rs.getDate("date"));
                itemsList.add(items);           
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return itemsList;
    }
    public void show_items(){
        ArrayList<ItemsM2> list=BindTable();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        Object[] row =new Object[6];
         //jTable1.setRowCount(0);
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getid();
            row[1]=list.get(i).getplace();
            row[2]=list.get(i).getaverage();
            row[3]=list.get(i).getcomplain();
            row[4]=list.get(i).getimage();
            row[5]=list.get(i).getdate();
            
            model.addRow(row); 
          
        }
        
//        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
//        Object[] row =new Object[5];
         //jTable1.setRowCount(0);
//        for(int i=0;i<list.size();i++){
//            row[0]=list.get(i).getid();
//            row[1]=list.get(i).getaverage();
//            row[2]=list.get(i).getcomplain();
////             if(list.get(i).getimage()==null){
//            row[3]=list.get(i).getimage();
//        }
//            ImageIcon image;
//            image = new ImageIcon(new ImageIcon(list.get(i).getimage()).getImage().getScaledInstance(150,120,Image.SCALE_SMOOTH));
//            model.addRow(row); 
//              row[3]=image;
          
//        }
//             else{
//                 row[3]=null;
//             }
//        }
      
        //DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
//        String[] columnName={"id","average","complain","image"};
//        Object[][] row =new Object[list.size()][4];
//         //jTable1.setRowCount(0);
//        for(int i=0;i<list.size();i++){
//            row[i][0]=list.get(i).getid();
//            row[i][1]=list.get(i).getaverage();
//            row[i][2]=list.get(i).getcomplain();
//            if(list.get(i).getimage()!=null){
//                ImageIcon image;
//                image = new ImageIcon(new ImageIcon(list.get(i).getimage()).getImage().getScaledInstance(150,120,Image.SCALE_SMOOTH));
//            
//            row[i][3]=image;
//            }
//            else{
//                    row[i][3]=null;
//                    }
//        }
//        TheModel model =new TheModel(row, columnName); 
//        jTable1.setModel(model); 
//        jTable1.setRowHeight(120);
//        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
          
        
        
    }
    public ArrayList<ItemsM> itemsList_4_place(){
        
        ArrayList<ItemsM> itemsList= new ArrayList();
         conn=MySQLConnect.ConnectDB();
         //String ss=jTextField1.getText();
        try {
            String query2="select id,place,average,complain,image,date from pokhara where place =?";
          pst = conn.prepareStatement(query2);
           pst.setString(1,jTextField4.getText());
            ResultSet rs=pst.executeQuery();
            ItemsM items;
            while(rs.next()){
                items=new ItemsM(rs.getInt("id"),rs.getString("place"),rs.getFloat("average"),rs.getString("complain"),rs.getString("image"),rs.getDate("date"));
                itemsList.add(items);           
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return itemsList;
    }
    public void show_items_by_place(){
        ArrayList<ItemsM> list=itemsList_4_place();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        Object[] row =new Object[6];
         //jTable1.setRowCount(0);
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getid();
            row[1]=list.get(i).getplace();
            row[2]=list.get(i).getaverage();
            row[3]=list.get(i).getcomplain();
            row[4]=list.get(i).getimage();
            row[5]=list.get(i).getdate();
            model.addRow(row); 
          
        }
        
    }
    
    
    public ArrayList<ItemsM> itemsList_4_date(){
        
        ArrayList<ItemsM> itemsList= new ArrayList();
         conn=MySQLConnect.ConnectDB();
         //String ss=jTextField1.getText();
        try {
            String query2="select id,place,average,complain,image,date from pokhara where date =?";
          pst = conn.prepareStatement(query2);
           pst.setString(1,jTextField5.getText());
            ResultSet rs=pst.executeQuery();
            ItemsM items;
            while(rs.next()){
                items=new ItemsM(rs.getInt("id"),rs.getString("place"),rs.getFloat("average"),rs.getString("complain"),rs.getString("image"),rs.getDate("date"));
                itemsList.add(items);           
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
          return itemsList;
    }
    public void show_items_by_date(){
        ArrayList<ItemsM> list=itemsList_4_date();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        Object[] row =new Object[6];
         //jTable1.setRowCount(0);
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getid();
            row[1]=list.get(i).getplace();
            row[2]=list.get(i).getaverage();
            row[3]=list.get(i).getcomplain();
            row[4]=list.get(i).getimage();
            row[5]=list.get(i).getdate();
            model.addRow(row); 
          
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POKHARA SYSTEM HANDLE");

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setText("show complaints");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "place", "avg_rating", "complain", "image", "date"
            }
        ));
        jTable1.setRowMargin(4);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("id");

        jLabel2.setText("place");

        jLabel3.setText("avg_rating");

        jLabel4.setText("complain");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPane1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel6.setText("Image");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel6.setMaximumSize(new java.awt.Dimension(335, 158));
        jLabel6.setMinimumSize(new java.awt.Dimension(335, 158));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(468, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setText("SEARCH BY PLACE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 255));
        jButton3.setText("SEARCH BY DATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("BACK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText("yyyy-mm-dd  english date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField5))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
               // ListSelectionModel model=jTable1.getSelectionModel();
        dtm.setRowCount(0);        
        show_items();
//        try {
//         conn=MySQLConnect.ConnectDB();
//         String query1="Select id, average, complain, image from pokhara";
//        
//            pst = conn.prepareStatement(query1);
//            rs=pst.executeQuery("select *from pokhara where place='"+jTextField1.getText()+"'");
//            
//            if(rs.next()){
//               byte[] img= rs.getBytes("Image");
//               ImageIcon image=new ImageIcon(img);
//               Image im=image.getImage();
//               Image myImg= im.getScaledInstance(label.getWidth(),label.getHeeight(),Image.SCALE_SMOOTH);
//               ImageIcon newImage=new ImageIcon(myImg);
//               label.setIcon(newImage);
//            }
//            else{
//                JOptionPane.showMessageDialog(null,"No Data");
//            }
//        
//        } catch (SQLException ex) {
//            Logger.getLogger(WelcomePokhara.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index= jTable1.getSelectedRow();
        TableModel model=jTable1.getModel();
        
        //JOptionPane.showMessageDialog(null,"haha");
        String id=model.getValueAt(index,0).toString();
        String place=model.getValueAt(index,1).toString();
        String avg_rating=model.getValueAt(index,2).toString();
        String complain=model.getValueAt(index,3).toString();
        String image=model.getValueAt(index,4).toString();
        String imagef= "C:\\xampp\\htdocs\\Ecofy\\uploads\\"+image;
        System.out.println(imagef);
        jTextField1.setText(id);
        jTextField2.setText(place);
        jTextField3.setText(avg_rating);
        jTextPane1.setText(complain);
       ImageIcon icon = new ImageIcon(imagef); 
       Image img=icon.getImage();
       Image newImg=img.getScaledInstance(jLabel6.getWidth(),jLabel6.getHeight(),Image.SCALE_SMOOTH);
       ImageIcon imagei;
        imagei = new ImageIcon(newImg);
       jLabel6.setIcon(imagei);
       jPanel2.add(jLabel6);
       
      
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);        
        show_items_by_place();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
         DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);        
        show_items_by_place();
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);        
        show_items_by_date();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
               new Login().setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(WelcomePokhara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePokhara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePokhara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePokhara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePokhara().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
