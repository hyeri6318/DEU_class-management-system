package cms.WarningPackage;

import java.util.ArrayList;

/**
 * 작성자: 정수연
 * 기능: 경고 인터페이스 구현
 */
public class Warning implements WarnSubject {
    private ArrayList observers;
    
    String name;  //이름
    String id;   //아이디
    int admin;  //관리자여부?
    int approve;  //인증여부?
    String warnNum;  //경고횟수
    String pw;   // 비밀번호
    String userType;   //사용자유형
    String telNum;   // 전화번호
    String email;    // 메일
    
    public Warning(){
        observers = new ArrayList();
    }
    
    // 옵저버 등록
    @Override
    public void registerObserver(WarnObserver observer) {
        observers.add(observer);
    }
    
    // 옵저버에 변경 값 알림
    public void notifyObserver(){
        for (int i = 0; i < observers.size(); i++) {
            WarnObserver observer = (WarnObserver) observers.get(i);
            observer.update(name, id, admin, approve, warnNum, pw, userType, telNum, email);
        }
    }
    public void setMeasurements(String name, String id, int admin, int approve, String warnNum, String pw,String userType,String telNum, String email) {
        this.name = name;
        this.id = id;
        this.admin = admin;
        this.approve = approve;
        this.warnNum = warnNum;
        this.pw = pw;
        this.userType = userType;
        this.telNum = telNum;
        this.email = email;
        notifyObserver();
    }
    
    public String getWarnNum(){
        return warnNum;
    }
    
    public String getID(){
        return id;
    }
    public String getname() {
        return name;
    }
    public String getPw() {
        return pw;
    }

    public String getUserType() {
        return userType;
    }

    public String getTelNum() {
        return telNum;
    }

    public String getEmail() {
        return email;
    }

    public int getadmin() {
        return admin;
    }

    public int getapprove() {
        return approve;
    }

    
}
