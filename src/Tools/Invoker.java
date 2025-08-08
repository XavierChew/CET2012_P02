package Tools;

import Exception.CustomException;
import Commands.Command;
import java.util.Stack;

/**
 * The {@code Invoker} class knows how to execute a given command
 */
public class Invoker {
    /**
     * Variable of an array to store the list of commands to execute.
     */
    private Command[] cmdToExecute;

    /**
     * Variable of commands to be executed.
     *
     * @param cmdToExecute an array of {@link Command} objects to execute
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * Executes all commands in the {@code cmdToExecute} array
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