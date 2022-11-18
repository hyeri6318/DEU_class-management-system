package cms.SchedulePackage;

import cms.ConnectDB.ConnectDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.sql.SQLException;

/**
 * 작성자: 정수연 기능: 실습실 상태 조회 가능
 */
public class SearchAvailable implements SearchState {

    private String state;  //좌석상태
    private String name;   //예약자명
    private String id;    //학번
    private int class_num;   //강의실명
    private int seat_num;     //좌석번호
    private String r_start_time;  //시작시간
    private String r_end_time;   //종료시간
    private int admin;     // 관리자 권한 여부
    private int approve;   // 인증여부

    private SearchAvailable(){};
    
    private static SearchAvailable instance = new SearchAvailable();
    
    public SearchAvailable getInstance(){
        return instance;
    }
    
    @Override
    public void search() {
        //Seat.addActionListener(this);
        System.out.println("실습실 상태 조회 가능합니다");
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        
        ArrayList<String> id_list = new ArrayList<String>();
        
        try {
            String sql = "select NAME, ID, R_STARTTIME,R_ENDTIME,ADMIN,APPROVE from reservation "+
                    "where CLASS_NUM =?, SEAT_NUM=?";  //915호에 예약된 모든 예약정보
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);   // sql문 전송
            ps.setInt(1,class_num);
            ps.setInt(2, seat_num);
            rs = ps.executeQuery();  //sql문 실행 요청 ResultSet rs
            
          
            while(rs.next()){
                String co = new String();
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }
}
