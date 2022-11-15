/*
작성자 : 이혜리
기능 : Command Pattern에서 Receiver의 역할로 요청자에게 결과를 제공하도록 구현함.
 */
package cms.ClassSearchPackage;

/**
 *
 * @author 이혜리
 */
public class TimeTable {

    public void timetable() {
        TimeTablePage time = new TimeTablePage();
        time.setVisible(true);
    }
}
