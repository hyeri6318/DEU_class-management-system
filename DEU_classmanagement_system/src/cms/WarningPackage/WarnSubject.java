package cms.WarningPackage;

/**
 * 작성자: 정수연
 * 기능: 좌석 경고 인터페이스
 */
public interface WarnSubject {
    void registerObserver(WarnObserver observer);
    void notifyObserver();
}
