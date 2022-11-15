/*
작성자 : 이혜리, 윤채민
기능 : 이벤트가 발생하는 객체 즉, SugObserver의 리스트를 가지고 있도록 구현함.
 */
package cmsSuggestionPackage;

/**
 *
 * @author 이혜리, 윤채민
 */
public interface SugSubject {
    public void registerObserver(SugObserver observer);
    public void notifyObserver();
}
