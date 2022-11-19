package cms.SchedulePackage;

import cms.UserPackage.ChangeInformPage;
import cms.UserPackage.DeleteInformPage;
import cms.UserPackage.LoginPage;
import cmsSuggestionPackage.PageSuggestion;

/**
 * 작성자: 정수연 기능: 교수 메인화면
 */
public class ProfessorMain extends javax.swing.JFrame {

    public ProfessorMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        change_button1 = new javax.swing.JButton();
        delete_button1 = new javax.swing.JButton();
        inputSemina = new javax.swing.JButton();
        inputSchedule = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("교수 메인화면");

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel1.setText("교수 메인화면");

        logoutBtn.setText("로그아웃");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        change_button1.setText("회원 정보 수정");
        change_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_button1ActionPerformed(evt);
            }
        });

        delete_button1.setText("회원 정보 삭제");
        delete_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_button1ActionPerformed(evt);
            }
        });

        inputSemina.setText("특강/세미나 입력");
        inputSemina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSeminaActionPerformed(evt);
            }
        });

        inputSchedule.setText("실습실 조회");
        inputSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputScheduleActionPerformed(evt);
            }
        });

        jButton1.setText("불편사항 제보");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inputSemina, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addComponent(inputSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(change_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(134, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(36, 36, 36)
                .addComponent(logoutBtn)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(112, 112, 112)
                    .addComponent(delete_button1)
                    .addGap(18, 18, 18)
                    .addComponent(change_button1)
                    .addGap(18, 18, 18)
                    .addComponent(inputSchedule)
                    .addGap(18, 18, 18)
                    .addComponent(inputSemina)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // 로그아웃 기능 구현
        LoginPage e = new LoginPage();
        e.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void change_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change_button1ActionPerformed
        // TODO add your handling code here:
        ChangeInformPage change = new ChangeInformPage();
        change.setVisible(true);
    }//GEN-LAST:event_change_button1ActionPerformed

    private void delete_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_button1ActionPerformed
        // TODO add your handling code here:
        DeleteInformPage delete = new DeleteInformPage();
        delete.setVisible(true);
    }//GEN-LAST:event_delete_button1ActionPerformed

    private void inputSeminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSeminaActionPerformed
        // 특강/세미나 입력 버튼
        InputSemina e = new InputSemina();
        e.setVisible(true);
        dispose();
    }//GEN-LAST:event_inputSeminaActionPerformed

    private void inputScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputScheduleActionPerformed
        // TODO add your handling code here:
        SeatStateView seat = new SeatStateView();
        seat.setVisible(true);
    }//GEN-LAST:event_inputScheduleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PageSuggestion suggest = new PageSuggestion();
        suggest.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfessorMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change_button1;
    private javax.swing.JButton delete_button1;
    private javax.swing.JButton inputSchedule;
    private javax.swing.JButton inputSemina;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logoutBtn;
    // End of variables declaration//GEN-END:variables
}
