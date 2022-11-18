package cms.SchedulePackage;

import cms.ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 작성자: 정수연 기능: 시간표 등록 기능 구현
 */
public class Schedule implements RegisterSchedule {

    //과목번호, 호실, 교수명, 과목명, 시작시간, 끝시간, 요일, 날짜
    //시간표는 날짜값을 받아들이지 않음 -> 날짜 값 null 입력
    String grade_num;  //과목번호
    String class_name;  //호실
    String pro_name;  //교수명
    String grade_name;  //과목명
    String start_time;  //시작시간
    String end_time;   //종료시간
    String week_day;   //요일

    @Override
    public void register() {   // 시간표 등록
        System.out.println("스케쥴-시간표 등록합니다.");
        ConnectDB db = new ConnectDB();   // DB 객체 생성
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("insert into schedule values (?,?,?,?,?,?,?,?)");

            ps.setString(1, grade_num);
            System.out.println("1");
            ps.setString(2, class_name);
            System.out.println("2");
            ps.setString(3, pro_name);
            System.out.println("3");
            ps.setString(4, grade_name);
            System.out.println("4");
            ps.setInt(5, 1);
            System.out.println("5");
            ps.setInt(6, 1);
            System.out.println("6");
            ps.setString(7, week_day);  //요일도 date로 값 받을 수 있음
            System.out.println("7");
            ps.setInt(8, 1);
            System.out.println("8");
            //8번째 날짜 값을 입력받지 않음

            ps.executeUpdate();
            System.out.println("success");
            
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
