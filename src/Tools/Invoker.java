package Tools;

import Exception.CustomException;
import Commands.Command;
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
     *
     * @param cmdToExecute command array
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * Execute command
     *
     * @param history command stack
     */
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : this.cmdToExecute) {
            try {
                cmd.execute();
                if (cmd.toBeSavedInHistory()) {
                    history.push(cmd);
                }
            }
            catch (CustomException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
