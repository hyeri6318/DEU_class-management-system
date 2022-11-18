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
        this.id = id;

        display();
    }

    public void admin() {   // 예약 취소시 관리자를 변경하기 위함.
        ConnectDB db = new ConnectDB();

        Connection conn = null;
        PreparedStatement query = null;
        PreparedStatement ps1 = null;
        Statement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        LoginPage lg = new LoginPage();

        try {
            conn = db.getConnection();
            st = conn.createStatement();

            rs1 = st.executeQuery("select * from reservation");

            ArrayList class_list = new ArrayList<String>();
            ArrayList id_list = new ArrayList<String>();

            while (rs1.next()) {
                class_list.add(rs1.getString("class_num"));
            }

            for (int i = 0; i < class_list.size(); i++) {
                query = conn.prepareStatement("update reservation set admin=0 where class_num='" + class_list.get(i) + "'");
                query.executeUpdate();

                rs2 = st.executeQuery("select id from reservation where class_num='" + class_list.get(i) + "' and approve=1 order by r_endtime desc");
            }

            while (rs2.next()) {
                id_list.add(rs2.getString("id"));
            }

            String adminStudent = id_list.get(0).toString();
            System.out.println(adminStudent);

            ps1 = conn.prepareStatement("update reservation set admin=1 where id='" + adminStudent + "'");
            ps1.executeUpdate();

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
                    admin();

                    JOptionPane.showMessageDialog(null, "실습실 예약 취소 되었습니다.");
                }
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "예약되어있지 않은 학생입니다.");
            ex.printStackTrace();
        }
    }
}
