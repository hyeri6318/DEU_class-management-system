/*
작성자 : 이혜리, 윤채민
기능 : 옵저버에 값을 등록하고 변경 값을 알리도록 구현함.
 */
package cmsSuggestionPackage;

import cms.ResCanclePackage.CancleObserver;
import java.util.ArrayList;

/**
 *
 * @author 이혜리, 윤채민
 */
public class Suggest implements SugSubject {

    private ArrayList observers;

    int suggestNum;
    String id;
    int classNum;
    String title;
    String content;

    public Suggest() {
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(SugObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            SugObserver observer = (SugObserver) observers.get(i);
            observer.update(suggestNum, id, classNum, title, content);
        }
    }

    public void setMeasurements(int suggestNum, String id, int classNum, String title, String content) {
        this.suggestNum = suggestNum;
        this.id = id;
        this.classNum = classNum;
        this.title = title;
        this.content = content;

        notifyObserver();
    }

    public int getSuggestNum() {
        return suggestNum;
    }

    public String getId() {
        return id;
    }

    public int getClassNum() {
        return classNum;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
