package Commands;

import Exception.CustomException;
import Tools.Receiver;

/**
 * ListCommand class listing the current data in storage
 */
public class ListCommand implements Command {
    /**
     * Variable of the receiver that performs the actual listing operation
     */
    private Receiver receiver;

    /**
     * Constructor for ListCommand
     *
     * @param receiver the receiver used to perform command
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the list command
     */
    @Override
    public void execute() {
        if (this.receiver == null) {
            throw new CustomException("Receiver cannot be null.");
        }
        receiver.list();
    }

    /**
     * Undo method for ListCommand
     */
    @Override
    public void undo() {}

    /**
     * Indicates whether this command should be saved in history.
     *
     * @return false since list operations should not be stored in history
     */
    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}
