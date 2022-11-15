/*
작성자 : 이혜리
기능 : ResSubject의 이벤트를 관찰하는 클래스로 값의 변경이 일어나면 하위 클래스인 ResdbUpdate에게 알려주도록 구현.
 */
package cms.ResStudentPackage;

/**
 *
 * @author 이혜리
 */
public interface ResObserver {

     void update(String name, String id, int class_num, int seat_num, String starttime, String endtime, String final_day, int admin, int approve );
}
