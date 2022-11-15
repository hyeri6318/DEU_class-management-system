package cms.SchedulePackage;

import cms.ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cms.SchedulePackage.RegisterSchedule;

/**
 작성자: 정수연
 기능: 특강 및 세미나 등록 기능 구현
 */
public class Seminar implements RegisterSchedule {
    @Override
    public void register(){   // 세미나 등록
        System.out.println("스케쥴-세미나 등록합니다.");
        ConnectDB db = new ConnectDB();  // DB 객체 생성
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            ps1 = conn.prepareStatement("alter session set nls_date_format = 'dd/MON/yyyy hh24:mi:ss");
            ps2 = conn.prepareStatement("insert into schedule values (?,?,?,?,?,?,?,?)");
            //과목번호, 호실, 교수명, 과목명, 시작시간, 끝시간, 요일, 날짜
            rs = ps2.executeQuery();
            
            ArrayList<String> year1 = new ArrayList<String>();  //년도
            ArrayList<String> month1 = new ArrayList<String>();  //월
            ArrayList<String> day1 = new ArrayList<String>();  //일
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
    }
}
