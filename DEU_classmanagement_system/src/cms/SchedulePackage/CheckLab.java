package cms.SchedulePackage;

/**
 * 작성자: 정수연 기능: 좌석 선택 창
 */
public class CheckLab extends javax.swing.JFrame {

    public CheckLab() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seat911 = new javax.swing.JButton();
        Seat915 = new javax.swing.JButton();
        Seat916 = new javax.swing.JButton();
        Seat918 = new javax.swing.JButton();
        UndoBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("강의실 선택");

        Seat911.setText("911호");
        Seat911.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Seat911ActionPerformed(evt);
            }
        });

        Seat915.setText("915호");
        Seat915.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Seat915ActionPerformed(evt);
            }
        });

        Seat916.setText("916호");
        Seat916.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Seat916ActionPerformed(evt);
            }
        });

        Seat918.setText("918호");
        Seat918.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Seat918ActionPerformed(evt);
            }
        });

        UndoBtn.setText("이전");
        UndoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel1.setText("강의실 선택");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(Seat911)
                        .addGap(18, 18, 18)
                        .addComponent(Seat915)
                        .addGap(26, 26, 26)
                        .addComponent(Seat916)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UndoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Seat918))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Seat911)
                    .addComponent(Seat915)
                    .addComponent(Seat916)
                    .addComponent(Seat918))
                .addGap(53, 53, 53)
                .addComponent(UndoBtn)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UndoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoBtnActionPerformed
        // 관리자(조교) 메인 화면으로 이동
        AssistantMain a = new AssistantMain();
        a.setVisible(true);
        dispose();
        //this.toBack();
    }//GEN-LAST:event_UndoBtnActionPerformed

    private void Seat911ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Seat911ActionPerformed
        // 911호로 이동
        /*SeatManage911 s911 = new SeatManage911();
        s911.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_Seat911ActionPerformed

    private void Seat915ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Seat915ActionPerformed
        // 915호로 이동
        /*SeatManage915 s915 = new SeatManage915();
        s915.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_Seat915ActionPerformed

    private void Seat916ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Seat916ActionPerformed
        // 916호로 이동
        /*SeatManage916 s916 = new SeatManage916();
        s916.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_Seat916ActionPerformed

    private void Seat918ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Seat918ActionPerformed
        // 918호로 이동
        /*SeatManage918 s918 = new SeatManage918();
        s918.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_Seat918ActionPerformed

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
            java.util.logging.Logger.getLogger(CheckLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckLab().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Seat911;
    private javax.swing.JButton Seat915;
    private javax.swing.JButton Seat916;
    private javax.swing.JButton Seat918;
    private javax.swing.JButton UndoBtn;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
