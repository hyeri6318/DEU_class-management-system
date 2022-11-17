/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.ClassSearchPackage;

import cms.ConnectDB.ConnectDB;
import cms.UserPackage.LoginPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lhl63
 */
public class ClassPlusTimePage extends javax.swing.JFrame {

    /**
     * Creates new form ClassPlusTimePage
     */
    public ClassPlusTimePage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        time_combobox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        plus_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        time_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00" }));

        jLabel1.setText("시간 연장");

        plus_button.setText("시간 연장");
        plus_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addComponent(time_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(plus_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(time_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(45, 45, 45)
                .addComponent(plus_button)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void plus_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus_buttonActionPerformed
        // TODO add your handling code here:
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement query = null;
        Statement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        LoginPage lg = new LoginPage();

        int classNum = 0;
        int seatNum = 0;
        int rtime = 0;

        int select_time = Integer.parseInt(time_combobox.getSelectedItem().toString());

        try {

            conn = db.getConnection();
            st = conn.createStatement();

            rs1 = st.executeQuery("select * from reservation where id = '" + lg.getID() + "'");   // 일치하는 아이디 -> 종료 시간 가져오기

            ArrayList class_list = new ArrayList<String>();
            ArrayList seat_list = new ArrayList<String>();
            ArrayList time_list = new ArrayList<String>();  // r_endtime 저장

            while (rs1.next()) {
                class_list.add(rs1.getString("class_num"));
                seat_list.add(rs1.getString("seat_num"));
                time_list.add(rs1.getString("r_endtime"));
            }

            for (int i = 0; i < time_list.size(); i++) {
                classNum = Integer.parseInt(class_list.get(i).toString());
                rtime = Integer.parseInt(time_list.get(i).toString().substring(0, 2));
                seatNum = Integer.parseInt(seat_list.get(i).toString());
            }

            if (select_time > rtime) {

                // reservation 전체 확인
                rs2 = st.executeQuery("select id from reservation where class_num='" + classNum + "' and seat_num='" + seatNum + "' and approve=1 and substr(r_starttime,0,2) > '" + select_time + "'");
                System.out.println("e");
                ArrayList id_list = new ArrayList<String>();

                while (rs2.next()) {
                    id_list.add(rs2.getString("id"));
                }

                if (id_list.isEmpty()) {
                    System.out.println("B");
                    query = conn.prepareStatement("UPDATE RESERVATION SET R_ENDTIME = '" + select_time + ":00:00' where id = '" + lg.getID() + "'");
                    query.executeUpdate();
                    JOptionPane.showMessageDialog(null, "시간 연장이 되었습니다.");
                } else {
                    System.out.println("A");
                    JOptionPane.showMessageDialog(null, "예약자가 있어 시간 연장이 불가능합니다.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_plus_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(ClassPlusTimePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassPlusTimePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassPlusTimePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassPlusTimePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassPlusTimePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton plus_button;
    private javax.swing.JComboBox<String> time_combobox;
    // End of variables declaration//GEN-END:variables
}
