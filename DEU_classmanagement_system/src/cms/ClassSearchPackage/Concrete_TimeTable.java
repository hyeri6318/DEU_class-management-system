/*
작성자 : 이혜리
기능 : Command Pattern에서 ConcreteCommand로 시간표 조회 기능을 구현함.
 */
package cms.ClassSearchPackage;

/**
 *
 * @author 이혜리
 */
public class Concrete_TimeTable implements Command{
        private final TimeTable timeReceiver;
    
    public Concrete_TimeTable(TimeTable timeReceiver){
        this.timeReceiver=timeReceiver;
    }
    
    @Override
    public void exectue() {
        timeReceiver.timetable();
    }
}
