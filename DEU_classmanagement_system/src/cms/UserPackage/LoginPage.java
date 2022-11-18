/*
작성자 : 이혜리
기능 : 로그인
 */
package cms.UserPackage;

import cms.ClassSearchPackage.StudentPage;
import cms.ConnectDB.ConnectDB;
import cms.ResAssistantPackage.PageAss;
import cms.SchedulePackage.ProfessorMain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author 이혜리
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
    }

    static String final_id = null;
    static String final_pw = null;
    static String final_name = null;

    private boolean LoginCompare(int check) {
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select id,pw,name from client");

            ArrayList<String> id_list = new ArrayList<String>();
            ArrayList<String> pw_list = new ArrayList<String>();
            ArrayList<String> name_list = new ArrayList<String>();

            while (rs.next()) {
                id_list.add(rs.getString("id"));
                pw_list.add(rs.getString("pw"));
                name_list.add(rs.getString("name"));
            }

            int ch = 0;
            int index = 0;

            for (int i = 0; i < id_list.size(); i++) {
                if (id_input.getText().equals(id_list.get(i)) && pw_input.getText().equals(pw_list.get(i))) {
                    JOptionPane.showMessageDialog(null, "로그인 성공");
                    index = i;
                    ch = -1;
                    if (check == 83) {
                        final_id = id_list.get(index);
                        final_pw = pw_list.get(index);
                        final_name = name_list.get(index);

                        //reset();
                        reservation_check();    // 예약 시간 확인 및 삭제

                        StudentPage student = new StudentPage();
                        student.setVisible(true);
                    } else if (check == 80) {
                        final_id = id_list.get(index);
                        final_pw = pw_list.get(index);

                        //reset()
                        ProfessorMain professor = new ProfessorMain();
                        professor.setVisible(true);
                    } else if (check == 65) {
                        final_id = id_list.get(index);
                        final_pw = pw_list.get(index);

                        //reset()
                        PageAss ass = new PageAss();
                        ass.setVisible(true);
                    } else if (check == 77) {
                        final_id = id_list.get(index);
                        final_pw = pw_list.get(index);

                        //reset()
                        CreateTokenPage token = new CreateTokenPage();
                        token.setVisible(true);
                    }
                    return true;
                }
            }

            if (ch == 0) {
                JOptionPane.showMessageDialog(null, "잘못된 입력입니다. 다시 로그인해 주세요.");
                id_input.setText(null);
                pw_input.setText(null);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void reservation_check() {  // 예약 시간 확인 및 삭제
        Date d1 = null;
        Date d2 = null;

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Date currentTime = new Date();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            d1 = f.parse(SimpleDateFormat.format(currentTime));

            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("select * from reservation where id='" + id_input.getText() + "'");

            ArrayList time_list = new ArrayList<String>();

            while (rs.next()) {
                time_list.add(rs.getString("r_endtime"));
            }

            if (time_list.isEmpty()) {

            } else {
                for (int i = 0; i < time_list.size(); i++) {
                    d2 = f.parse(time_list.get(i).toString());
                }

                if (d1.compareTo(d2) >= 0) {
                    ps = conn.prepareStatement("delete from reservation where id='" + id_input.getText() + "'");
                    ps.executeUpdate();
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reset() {   // 3/1 혹은 9/1일 경우 초기화
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM/dd");
        String currentDate = localDateTime.format(formatTime);

        try {
            conn = db.getConnection();

            if (currentDate.equals("11/18") || currentDate.equals("9/1")) {
                ps1 = conn.prepareStatement("delete from client where id<>2022");
                ps2 = conn.prepareStatement("delete from token");

                ps1.executeUpdate();
                ps2.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String ID = null;
    private String PW = null;
    private String name = null;

    public String getID() {
        return final_id;
    }

    public void setID(String ID) {
        this.ID = final_id;
    }

    public String getPW() {
        return final_pw;
    }

    public void setPW(String PW) {
        this.PW = final_pw;
    }

    public String getName() {
        return final_name;
    }

    public void setName(String name) {
        this.name = final_name;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        id_input = new javax.swing.JTextField();
        pw_input = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        login1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("아이디");

        jLabel2.setText("비밀번호");

        login.setText("로그인");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel6.setText("로그인");

        login1.setText("회원가입");
        login1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login1MouseClicked(evt);
            }
        });
        login1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(25, 25, 25)
                            .addComponent(pw_input, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(99, 99, 99))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(37, 37, 37)
                            .addComponent(id_input, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(89, 89, 89)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(login)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(login1)
                        .addGap(119, 119, 119))))
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pw_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login)
                    .addComponent(login1))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // TODO add your handling code here:
        /*
        int number = jTable1.getSelectedRow();
        lecture_num = jTable1.getValueAt(number, 0).toString();
         */
    }//GEN-LAST:event_loginMouseClicked

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        char first = id_input.getText().charAt(0);

        switch (first) { // 추후 사용자별로 페이지 연결하기
            case 83:
                check = LoginCompare('S');
                if (check) {
                    System.out.println("학생 로그인 성공");
                    break;
                } else {
                    break;
                }
            case 80:
                check = LoginCompare('P');
                if (check) {
                    System.out.println("교수 로그인 성공");
                    break;
                } else {
                    break;
                }
            case 65:
                check = LoginCompare('A');
                if (check) {
                    System.out.println("조교 로그인 성공");
                    break;
                } else {
                    break;
                }
            case 77:
                check = LoginCompare('M');
                if (check) {
                    System.out.println("마스터 조교 로그인 성공");
                    break;
                } else {
                    break;
                }
        }
    }//GEN-LAST:event_loginActionPerformed

    private void login1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_login1MouseClicked

    private void login1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login1ActionPerformed
        // TODO add your handling code here:
        JoinPage join = new JoinPage();
        join.setVisible(true);
        
    }//GEN-LAST:event_login1ActionPerformed

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField id_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton login;
    private javax.swing.JButton login1;
    private javax.swing.JPasswordField pw_input;
    // End of variables declaration//GEN-END:variables
}
