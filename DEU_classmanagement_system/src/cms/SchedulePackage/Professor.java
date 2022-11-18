package cms.SchedulePackage;

/**
 * 작성자: 정수연 기능: 교수 정보
 */
public class Professor extends Admin {

    public Professor() {   // 조교 클래스 생성자. 조교가 할 행동 정의
        setRegisterSchedule(new Schedule());
        setRegisterSchedule(new Seminar());   
    }

    @Override
    public void display() {  
        System.out.println("교수입니다.");
    }
}
