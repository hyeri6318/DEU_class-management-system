/*
작성자 : 이혜리
기능 : 옵저버에 값을 등록하고 변경 값을 알리도록 구현함.
 */
package cms.ResStudentPackage;

import java.util.ArrayList;

/**
 *
 * @author 이혜리
 */
public class Reservation implements ResSubject {

    private ArrayList observers;

    String name;
    String id;
    int class_num;
    int seat_num;
    String starttime;
    String endtime;
    String final_day;
    int admin;
    int approve;

    public Reservation() {
        observers = new ArrayList();
    }

    // 옵저버 등록
    @Override
    public void registerObser(ResObserver observer) {
        observers.add(observer);
    }

    // 옵저버에 변경 값 알림
    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            ResObserver observer = (ResObserver) observers.get(i);
            observer.update(name, id, class_num, seat_num, starttime, endtime, final_day, admin, approve);
        }
    }

    public void setMeasurements(String name, String id, int class_num, int seat_num, String starttime, String endtime, String final_day, int admin, int approve) {
        this.name = name;
        this.id = id;
        this.class_num = class_num;
        this.seat_num = seat_num;
        this.starttime = starttime;
        this.endtime = endtime;
        this.final_day = final_day;
        this.admin = admin;
        this.approve = approve;

        notifyObserver();
    }

    public String getname() {
        return name;
    }

    public String getid() {
        return id;
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

    public String getfinal_day() {
        return final_day;
    }

    public int getadmin() {
        return admin;
    }

    public int getapprove() {
        return approve;
    }
}
