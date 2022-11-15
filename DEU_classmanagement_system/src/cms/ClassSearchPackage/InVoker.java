/*
작성자 : 이혜리
기능 : Command Pattern에서 Invoker 기능으로 기능 실행을 요청함.
 */
package cms.ClassSearchPackage;

/**
 *
 * @author 이혜리
 */
public class InVoker {

    Command theCommand;

    public InVoker(Command theCommand) {
        setCommand(theCommand);
    }

    public void setCommand(Command newCommand) {
        this.theCommand = newCommand;
    }

    public void pressed() {
        theCommand.exectue();
    }
}
