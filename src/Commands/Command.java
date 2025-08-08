package Commands;

import Exception.CustomException;

/**
 * The Command interface defines the contract for all command classes
 */
public interface Command {

    /**
     * Executes the command
     */
    public void execute();

    /**
     * Undoes the previously executed command
     */
    public void undo();

    /**
     * Determines whether this command should be saved in the command history
     *
     */
    public boolean toBeSavedInHistory();
}
