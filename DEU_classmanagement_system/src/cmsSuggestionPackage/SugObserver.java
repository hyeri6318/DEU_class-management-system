/*
작성자 : 이혜리, 윤채민
기능 : SugSubject의 이벤트를 관찰하는 클래스로 값의 변경이 일어나면 하위 클래스인 SugdbUpdate에게 알려주도록 구현.
 */
package cmsSuggestionPackage;

/**
 *
 * @author 이혜리, 윤채민
 */
public interface SugObserver {
    void update(int suggestNum, String id, int classNum, String title, String content);
}
