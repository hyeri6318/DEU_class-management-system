/*
작성자 : 이혜리
기능 : 옵저버에 값을 제거하고 변경 값을 알리도록 구현함.
 */
package cms.ResCanclePackage;

import java.util.ArrayList;

/**
 *
 * @author 이혜리
 */
public class Cancle implements CancleSubject {

    private ArrayList observers;

    String id;

    public Cancle() {
        observers = new ArrayList();
    }

    // 옵저버 등록
    @Override
    public void removeObserver(CancleObserver observer) {
        observers.add(observer);
    }

    // 옵저버에 변경 값 알림
    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            CancleObserver observer = (CancleObserver) observers.get(i);
            observer.update(id);
        }
    }

    public void setMeasurements(String id) {
        this.id = id;

        notifyObserver();
    }

    public String getid() {
        return id;
    }
}
