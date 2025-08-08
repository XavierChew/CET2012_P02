package Commands;

import Exception.CustomException;

/**
 * The Command interface defines the contract for all command classes.
 * Each command must support execution, undo functionality, and
 * indicate whether it should be saved in the command history.
 */
public interface Command {

    /**
     * Executes the command.
     * Implementing classes will define the specific logic to be performed when this method is called.
     *
     * @throws CustomException if the command cannot be executed properly
     */
    public void execute();

    /**
     * Undoes the previously executed command.
     * Implementing classes should revert any changes made during the execute method.
     */
    public void undo();

    /**
     * Determines whether this command should be saved in the command history.
     * Some commands, such as list or undo, may not need to be stored.
     *
     * @return true if the command should be saved in history; false otherwise
     */
    public boolean toBeSavedInHistory();
}
