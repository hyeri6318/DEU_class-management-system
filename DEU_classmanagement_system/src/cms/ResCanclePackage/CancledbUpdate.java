/*
작성자 : 이혜리
기능 : CancleObserver 통해 CancleSubject 이벤트를 감지하도록 구현함.
 */
package cms.ResCanclePackage;

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
 * @author 이혜리
 */
public class CancledbUpdate implements CancleObserver {

    String id;

    private CancleSubject cancle;

    public CancledbUpdate(CancleSubject cancle) {
        this.cancle = cancle;
        cancle.removeObserver(this);
    }

    @Override
    public void update(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.id = id;

        display();
    }

    public void display() {

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("select approve from Reservation where id='" + id + "'");

            ArrayList<String> a_list = new ArrayList<String>();

            while (rs.next()) {
                a_list.add(rs.getString("approve"));
            }
            for (int i = 0; i < a_list.size(); i++) {
                if ((a_list.get(i)).equals("1")) {
                    ps = conn.prepareStatement("delete from Reservation where id='" + id + "'");
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "실습실 예약 취소 되었습니다.");
                }
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
