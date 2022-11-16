/*
작성자 : 이혜리
기능 : ResObserver를 통해 ResSubjet의 이벤트를 감지하도록 구현함.
 */
package cms.ResStudentPackage;

import cms.ClassSearchPackage.SeatSearchPage;
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
public class ResdbUpdate implements ResObserver {

    String name;
    String id;
    int class_num;
    int seat_num;
    String starttime;
    String endtime;
    String final_day;
    int admin;
    int approve;
    private ResSubject reservation;

    public ResdbUpdate(ResSubject reservation) {
        this.reservation = reservation;
        reservation.registerObser(this);
    }

    @Override
    public void update(String name, String id, int class_num, int seat_num, String starttime, String endtime, String final_day, int admin, int approve) {
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.name = name;
        this.id = id;
        this.class_num = class_num;
        this.seat_num = seat_num;
        this.starttime = starttime;
        this.endtime = endtime;
        this.final_day = final_day;
        this.admin = admin;
        this.approve = approve;

        display();
    }

    public void display() {

        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;

        SeatSearchPage seat = new SeatSearchPage();
        boolean r_check = seat.r_check();   // 예약 여부 확인
        boolean t_check = seat.timecheck(); // 예약 시간 확인 (16시 30분 이전 이후)
        boolean w_check = seat.w_check(); // 경고 횟수에 따른 예약 가능 여부 확인

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("insert into Reservation values(?,?,?,?,?,?,?,?,?)");

//            if (t_check) {
                if (r_check) {
                    if (w_check) {
                        ps.setString(1, name);  // 이름 String
                        ps.setString(2, id);    // 아이디 String
                        ps.setInt(3, class_num); // 예약할 실습실 번호 int
                        ps.setInt(4, seat_num); // 좌석 번호 int
                        ps.setString(5, starttime); // 시작시간 date
                        ps.setString(6, endtime); // 끝시간 date
                        ps.setString(7, final_day); // 요일 day
                        ps.setInt(8, admin); // 관리자 여부 int
                        ps.setInt(9, approve); // 승인여부 int

                        ps.executeUpdate();
                    } else {
                        JOptionPane.showMessageDialog(null, "경고로 인해 예약이 제한되었습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "이미 예약 되었습니다.");
                }
//            }else{
//                JOptionPane.showMessageDialog(null, "예약 시간이 지났습니다.");
//            }

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
