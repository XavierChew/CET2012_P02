import java.util.ArrayList;
import java.util.Stack;

public class Invoker {
    private Command command;

    public void setCommandsForExecution(Command cmdToExecute) {
        this.command = cmdToExecute;
    }

    public void executeCommand() {
        this.command.execute();
    }

    //we need to use this:
    //private Command[] commands;
    //public void setCommandsForExecution(Command[] cmdToExecute) {
    //}

    //public void executeCommand(Stack<Command> history) {
    //}
}