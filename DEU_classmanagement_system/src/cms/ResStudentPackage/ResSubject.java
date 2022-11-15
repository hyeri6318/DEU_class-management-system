/*
작성자 : 이혜리
기능 : 이벤트가 발생하는 객체 즉, ResObserver의 리스트를 가지고 있도록 구현함.
 */
package cms.ResStudentPackage;

/**
 *
 * @author 이혜리
 */
public interface ResSubject {

    void registerObser(ResObserver observer);

    void notifyObserver();
}
