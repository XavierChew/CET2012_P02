package Commands;

import Exception.CustomException;
import Tools.Receiver;

/**
 * ListCommand class that implements the Command interface.
 * This command is responsible for listing the current data stored by the receiver.
 */
public class ListCommand implements Command {
    /**
     * The receiver that performs the actual listing operation.
     */
    private Receiver receiver;

    /**
     * Constructor for ListCommand.
     *
     * @param receiver the receiver object used to perform operations
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the ListCommand by calling the list() method on the receiver.
     *
     * @throws CustomException if the receiver is null
     */
    @Override
    public void execute() {
        if (this.receiver == null) {
            throw new CustomException("Receiver cannot be null.");
        }
        receiver.list();
    }

    /**
     * Undo method for ListCommand.
     * This method is intentionally left empty because listing does not change any state.
     */
    @Override
    public void undo() {}

    /**
     * Indicates whether this command should be saved in history.
     * For ListCommand, it always returns false.
     *
     * @return false since list operations should not be stored in history
     */
    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}
