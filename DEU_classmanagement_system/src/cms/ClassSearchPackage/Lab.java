/*
작성자 : 이혜리
기능 : Command Pattern에서 Receiver의 역할로 요청자에게 결과를 제공하도록 하는 클래스
 */
package cms.ClassSearchPackage;

/**
 *
 * @author 이혜리
 */
public class Lab {

    public void lab() {
        // 예약한 실습실 좌석 배치도 페이지로 이동
        SeatSearchPage seat = new SeatSearchPage();
        seat.setVisible(true);
    }
}
