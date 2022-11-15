package cms.WarningPackage;

import cms.ClassSearchPackage.SeatSearchPage;
import cms.ConnectDB.ConnectDB;
import cms.ResStudentPackage.ResSubject;
import cms.UserPackage.LoginPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * 작성자: 정수연
 * 기능: 좌석 경고 업데이트 [옵저버] 구현 (구독자)
 * concreteObserver
 */
public class WarndbUpdate implements WarnObserver {

    String name;
    String id;
    int class_num;
    int seat_num;
    String starttime;
    String endtime;
    int admin;
    int approve;
    String warnNum;
    private WarnSubject warning;

    public WarndbUpdate(WarnSubject warning) {
        this.warning = warning;
        warning.registerObserver(this);
    }
    
    
    
    @Override
    public void update(String name, String id, int class_num, int seat_num, String starttime, String endtime, int admin, int approve, String warnNum) {
        this.name = name;
        this.id = id;
        this.class_num = class_num;
        this.seat_num = seat_num;
        this.starttime = starttime;
        this.endtime = endtime;
        this.admin = admin;
        this.approve = approve;
        this.warnNum = warnNum;

        display();
    }

    // 좌석 경고 버튼 구현
    private void display() {
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;
        
        SeatSearchPage seat = new SeatSearchPage();
        boolean r_check = seat.r_check();

        
        try {
            conn = db.getConnection();
            String sql1 = "select * from Reservation";    // 예약 db 값 전부 불러오기
            ps = conn.prepareStatement(sql1);

            if(r_check){
            ps.setString(1, warnNum);  // 경고 횟수 String
            ps.setString(2, id);    // 아이디 String

            String sql2 = "update Client set WARNING(?) where id = '"+id+"'";   // 사용자 경고 횟수 업데이트
            ps = conn.prepareStatement(sql2);
            
            
            
            ps.executeUpdate();  // 경고 횟수 업데이트 (select문이 아님)
            JOptionPane.showMessageDialog(null, "예약 되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "이미 예약 되었습니다.");
            }
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
}
