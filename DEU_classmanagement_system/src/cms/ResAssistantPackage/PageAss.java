/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.ResAssistantPackage;

import cms.SchedulePackage.InputSchedule;
import cms.SchedulePackage.SeatStateView;
import cms.UserPackage.ChangeInformPage;
import cms.UserPackage.DeleteInformPage;
import cms.UserPackage.LoginPage;
import cmsSuggestionPackage.CheckSuggestionPage;

/**
 *
 * @author yooun
 */
public class PageAss extends javax.swing.JFrame {

    /**
     * Creates new form PageAss
     */
    public PageAss() {
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

        logoutBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        change_button = new javax.swing.JButton();
        searchschedule_button = new javax.swing.JButton();
        searchclass_button = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ResAccept = new javax.swing.JButton();
        ResCancle = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutBtn.setText("로그아웃");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel2.setText("조교 메인화면");

        change_button.setText("회원 정보 수정");
        change_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_buttonActionPerformed(evt);
            }
        });

        searchschedule_button.setText("시간표 입력");
        searchschedule_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchschedule_buttonActionPerformed(evt);
            }
        });

        searchclass_button.setText("실습실 조회 / 좌석 경고");
        searchclass_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchclass_buttonActionPerformed(evt);
            }
        });

        jButton1.setText("제보 확인");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ResAccept.setText("예약 승인");
        ResAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResAcceptActionPerformed(evt);
            }
        });

        ResCancle.setText("승인 취소");
        ResCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResCancleActionPerformed(evt);
            }
        });

        delete_button.setText("회원 정보 삭제");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(315, Short.MAX_VALUE)
                        .addComponent(logoutBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel2)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(122, 122, 122)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ResAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ResCancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchclass_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchschedule_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(change_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(123, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(change_button)
                    .addGap(18, 18, 18)
                    .addComponent(delete_button)
                    .addGap(18, 18, 18)
                    .addComponent(ResAccept)
                    .addGap(18, 18, 18)
                    .addComponent(ResCancle)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addComponent(searchclass_button)
                    .addGap(18, 18, 18)
                    .addComponent(searchschedule_button)
                    .addContainerGap(90, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // 로그아웃 기능 구현
        LoginPage e = new LoginPage();
        e.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void change_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change_buttonActionPerformed
        // TODO add your handling code here:
        ChangeInformPage change = new ChangeInformPage();
        change.setVisible(true);
    }//GEN-LAST:event_change_buttonActionPerformed

    private void searchschedule_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchschedule_buttonActionPerformed
        // TODO add your handling code here:
        InputSchedule schedule = new InputSchedule();
        schedule.setVisible(true);
    }//GEN-LAST:event_searchschedule_buttonActionPerformed

    private void searchclass_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchclass_buttonActionPerformed
        // TODO add your handling code here:
        SeatStateView seat = new SeatStateView();
        seat.setVisible(true);
    }//GEN-LAST:event_searchclass_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CheckSuggestionPage check = new CheckSuggestionPage();
        check.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ResAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResAcceptActionPerformed
        //예약 승인 요청 사용자 처리 화면으로 전환
        PageRes pageRes = new PageRes();
        pageRes.setVisible(true);
        dispose();
    }//GEN-LAST:event_ResAcceptActionPerformed

    private void ResCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResCancleActionPerformed
        //예약 승인된 사용자 취소 처리 화면으로 전환
        PageUnres pageUnres = new PageUnres();
        pageUnres.setVisible(true);
        dispose();
    }//GEN-LAST:event_ResCancleActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        DeleteInformPage delete = new DeleteInformPage();
        delete.setVisible(true);
    }//GEN-LAST:event_delete_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(PageAss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PageAss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PageAss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PageAss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PageAss().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ResAccept;
    private javax.swing.JButton ResCancle;
    private javax.swing.JButton change_button;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton searchclass_button;
    private javax.swing.JButton searchschedule_button;
    // End of variables declaration//GEN-END:variables
}
