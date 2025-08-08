package Commands;

import Exception.CustomException;

/**
 * Commands interface
 */
public interface Command {

    /**
     * Commands execute method
     */
    public void execute();
    /**
     * Commands undo method
     */
    public void undo();
    /**
     * To decide if this command need to save in history
     */
    public boolean toBeSavedInHistory();
}