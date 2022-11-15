/*
작성자 : 이혜리
기능 : 이벤트가 발생하는 객체 즉, CancleObserver 리스트를 가지고 있도록 구현함.
 */
package cms.ResCanclePackage;

/**
 *
 * @author 이혜리
 */
public interface CancleSubject {

    void removeObserver(CancleObserver observer);
    void notifyObserver();
}
