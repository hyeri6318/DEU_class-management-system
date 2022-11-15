package cms.SchedulePackage;

/**
 * 작성자: 정수연 기능: 교수 정보
 */
public class Professor extends Admin {

    public Professor() {   // 조교 클래스 생성자. 조교가 할 행동 정의
//        searchState = new SearchAvailable();  // 실습실 좌석 조회 가능
//        registerSchedule = new Schedule();   // 시간표 입력
//        registerSchedule = new Seminar();  // 세미나 입력
        setRegisterSchedule(new Schedule());
        setRegisterSchedule(new Seminar());
        //setSearchState(new SearchAvailable());
        
    }

    @Override
    public void display() {  
        System.out.println("교수입니다.");
    }
}
