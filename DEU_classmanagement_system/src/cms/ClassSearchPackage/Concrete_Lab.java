/*
작성자 : 이혜리
기능 : Command Pattern에서 ConcreteCommand로 실습실 조회 기능을 구현함.
 */
package cms.ClassSearchPackage;


/**
 *
 * @author 이혜리
 */
public class Concrete_Lab implements Command {

    private final Lab labReceiver;

    public Concrete_Lab(Lab labReceiver) {
        this.labReceiver = labReceiver;
    }

    @Override
    public void exectue() {
        labReceiver.lab();
    }
}
