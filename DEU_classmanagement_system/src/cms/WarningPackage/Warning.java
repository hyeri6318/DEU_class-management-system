package cms.WarningPackage;

import java.util.ArrayList;

/**
 * 작성자: 정수연
 * 기능: 경고 인터페이스 구현
 */
public class Warning implements WarnSubject {
    private ArrayList observers;
    
    String id;   // 학번
    String warnNum; // 경고횟수
    String name;
    int class_num;
    int seat_num;
    String starttime;
    String endtime;
    int admin;
    int approve;
    
    public Warning(){
        observers = new ArrayList();
    }
    
    
    // 옵저버에 변경 값 알림
    public void notifyObserver(){
        for (int i = 0; i < observers.size(); i++) {
            WarnObserver observer = (WarnObserver) observers.get(i);
            observer.update(name, id, class_num, seat_num, starttime, endtime, admin, approve, warnNum);
        }
    }
    public void setMeasurements(String name, String id, int class_num, int seat_num, String starttime, String endtime, int admin, int approve, String warnNum) {
        this.name = name;
        this.id = id;
        this.class_num = class_num;
        this.seat_num = seat_num;
        this.starttime = starttime;
        this.endtime = endtime;
        this.admin = admin;
        this.approve = approve;
        this.warnNum = warnNum;

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
    public int getclass_num() {
        return class_num;
    }

    public int getseat_num() {
        return seat_num;
    }

    public String getstarttime() {
        return starttime;
    }

    public String getendtime() {
        return endtime;
    }

    public int getadmin() {
        return admin;
    }

    public int getapprove() {
        return approve;
    }

    // 옵저버 등록
    @Override
    public void registerObserver(WarnObserver observer) {
        observers.add(observer);
    }
    
}
