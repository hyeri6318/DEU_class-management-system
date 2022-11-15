package cms.WarningPackage;

import cms.ConnectDB.ConnectDB;
import cms.SchedulePackage.SeatStateView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 * 작성자: 정수연
 * 기능: 좌석 경고 업데이트 [옵저버] 구현 (구독자)
 * concreteObserver
 */
public class WarndbUpdate implements WarnObserver {

    //사용자 db 정보 모두 입력
    String name;  //이름
    String id;   //아이디
    int admin;  //관리자여부?
    int approve;  //인증여부?
    String warnNum;  //경고횟수
    String pw;   // 비밀번호
    String userType;   //사용자유형
    String telNum;   // 전화번호
    String email;    // 메일
    
    
    private WarnSubject warning;

    public WarndbUpdate(WarnSubject warning) {
        this.warning = warning;
        warning.registerObserver(this);
    }
    
    
    @Override
    public void update(String name, String id, int admin, int approve, String warnNum, String pw,String userType,String telNum, String email) {
        this.name = name;
        this.id = id;
        this.admin = admin;
        this.approve = approve;
        this.warnNum = warnNum;
        this.pw = pw;
        this.userType = userType;
        this.telNum = telNum;
        this.email = email;

        display();
    }

    // 좌석 경고 버튼 구현
    private void display() {
        
        ConnectDB db = new ConnectDB();
        Connection conn = null;
        PreparedStatement ps = null;
        
        SeatStateView seat = new SeatStateView();  // 좌석 경고 화면 이동

        try {
            conn = db.getConnection();
            String sql = "update Client set warning = warning+1 where id = ?";    // 예약 db 값 전부 불러오기
            ps = conn.prepareStatement(sql);

            
            ps.setString(1,id);    // 아이디 String

            ps.executeUpdate();  // 경고 횟수 업데이트 (select문이 아님)
            JOptionPane.showMessageDialog(null, "경고 완료");
            
            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "경고 실패");
            ex.printStackTrace();
        }

    }
    
}
