package Tools;

import Exception.CustomException;
import Commands.Command;
import java.util.Stack;

/**
 * The {@code Invoker} class is responsible for executing a set of commands
 * and managing the command history stack. It is a key component of the
 * Command design pattern.
 */
public class Invoker {
    /**
     * Array to store the list of commands to execute.
     */
    private Command[] cmdToExecute;

    /**
     * Sets the commands to be executed.
     *
     * @param cmdToExecute an array of {@link Command} objects to execute
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * Executes all commands in the {@code cmdToExecute} array.
     * If a command throws a {@link CustomException}, the error is printed
     * and execution continues with the next command.
     * <p>
     * Commands that indicate they should be stored in history (via
     * {@code toBeSavedInHistory()}) are pushed onto the {@code history} stack.
     *
     * @param history the {@link Stack} used to store executed commands for undo operations
     */
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : this.cmdToExecute) {
            try {
                cmd.execute();
                if (cmd.toBeSavedInHistory()) {
                    history.push(cmd);
                }
            } catch (CustomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
