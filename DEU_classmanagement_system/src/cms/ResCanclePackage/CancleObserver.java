/*
작성자 : 이혜리
기능 : CancleSubject 이벤트를 관찰하는 클래스로 값의 변경이 일어나면 하위 클래스인 CancledbUpdate에게 알려주도록 구현.
 */
package cms.ResCanclePackage;

/**
 *
 * @author 이혜리
 */
public interface CancleObserver {
    void update(String id);
}
