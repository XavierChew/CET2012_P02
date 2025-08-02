import java.util.Stack;

/**
 * Invoker class:
 */
public class Invoker {
    /**
     * Variable for command array
     */
    private Command[] cmdToExecute;

    /**
     * Set commands for execution
     * @param cmdToExecute command array
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * Execute command
     * @param history Stack of command
     */
    public void executeCommand(Stack<Command> history) {
        Command command = history.lastElement();
        command.execute();
    }
}