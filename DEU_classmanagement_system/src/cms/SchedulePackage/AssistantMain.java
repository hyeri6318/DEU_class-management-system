package cms.SchedulePackage;

import cms.UserPackage.LoginPage;
//import cms.WarningPackage.SeatWarning;

/**
 작성자: 정수연
 기능: 조교 메인화면
 */
public class AssistantMain extends javax.swing.JFrame {

    public AssistantMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        InputTimeTable = new javax.swing.JButton();
        CheckLab = new javax.swing.JButton();
        LogoutBtn = new javax.swing.JButton();
        seatWarning = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("조교 메인화면");

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel1.setText("조교 메인화면");

        InputTimeTable.setText("수업 시간표 입력");
        InputTimeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTimeTableActionPerformed(evt);
            }
        });

        CheckLab.setText("실습실 상황 조회");
        CheckLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckLabActionPerformed(evt);
            }
        });

        LogoutBtn.setText("로그아웃");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        seatWarning.setText("좌석 경고");
        seatWarning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatWarningActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CheckLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InputTimeTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(seatWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LogoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(InputTimeTable)
                .addGap(31, 31, 31)
                .addComponent(CheckLab)
                .addGap(29, 29, 29)
                .addComponent(seatWarning)
                .addGap(30, 30, 30)
                .addComponent(LogoutBtn)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        // 로그아웃 버튼 구현
        LoginPage e = new LoginPage();
        e.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void CheckLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckLabActionPerformed
        // 실습실 상황 조회 기능 구현 -> 강의실 선택 화면 이동
        CheckLab e = new CheckLab();
        e.setVisible(true);
        dispose();
    }//GEN-LAST:event_CheckLabActionPerformed

    private void InputTimeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTimeTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTimeTableActionPerformed

    private void seatWarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatWarningActionPerformed
        // 좌석 경고 버튼 구현
        /*SeatWarning w = new SeatWarning();
        w.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_seatWarningActionPerformed

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssistantMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckLab;
    private javax.swing.JButton InputTimeTable;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton seatWarning;
    // End of variables declaration//GEN-END:variables
}
