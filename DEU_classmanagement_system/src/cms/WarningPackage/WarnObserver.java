package cms.WarningPackage;

/**
 * 작성자: 정수연
 * 기능: 좌석 경고 이벤트 관찰 클래스(주제)
 */
public interface WarnObserver {
    void update(String name, String id, int admin, int approve, String warnNum, String pw,String userType,String telNum, String email);
}
