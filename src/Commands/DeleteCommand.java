package Commands;

import Exception.CustomException;
import Tools.Receiver;

/**
 * The {@code DeleteCommand} class deleting an item data storage
 */
public class DeleteCommand implements Command {
    /**
     * Variable of the receiver that performs the actual listing operation
     */
    private Receiver receiver;

    /**
     * Variable of the delete command
     */
    private String strDeleteCommand;

    /**
     * Variable of string for undoing delete
     */
    private String deletedString = "";

    /**
     * Variable of the index from which the string was deleted
     */
    private int deletedIndex;

    /**
     * Variable of the flag to determine whether this command should be added to history
     */
    private boolean toHistory = true;

    /**
     * Constructor for DeleteCommand
     *
     * @param receiver the receiver that performs the command
     * @param strDeleteCommand  the command string to be executed
     */
    public DeleteCommand(Receiver receiver, String strDeleteCommand) {
        this.receiver = receiver;
        this.strDeleteCommand = strDeleteCommand;
    }

    /**
     * Executes the delete command
     */
    public void execute() {
        if (this.receiver == null) {
            toHistory = false;
            throw new CustomException("Receiver cannot be null.");
        }

        if (this.strDeleteCommand == null || this.strDeleteCommand.isEmpty()) {
            toHistory = false;
            throw new CustomException("command cannot be null.");
        }

        String[] splitDeleteCommand = this.strDeleteCommand.split("\\s+");

        if (receiver.getStorageSize() < 1) {
            toHistory = false;
            throw new CustomException("Nothing to delete.");
        }

        // Check that the command contains only one argument
        if (splitDeleteCommand.length != 1) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        // Validate the index format
        int deletedIndex = -1;

        try {
            deletedIndex = Integer.parseInt(splitDeleteCommand[0]) - 1;
        }
        catch (Exception e) {
            throw new CustomException("Invalid index to delete");
        }
        if(deletedIndex < 0) {
            throw new CustomException("Index cannot be less than 0");
        }
        if(deletedIndex >= receiver.getStorageSize()) {
            throw new CustomException("Index position exceed data size");
        }


        if (this.deletedIndex >= receiver.getStorageSize()) {
            throw new CustomException("Index out of bounds.");
        }

        this.deletedString = this.receiver.get(this.deletedIndex);
        this.receiver.delete(this.deletedIndex);
    }

    /**
     * Undoes the delete command by reinserting the deleted string at its original index
     */
    public void undo() {
        if (this.deletedString == null) {
            toHistory = false;
            throw new CustomException("Error while performing undo.");
        }
        this.receiver.insert(this.deletedIndex, this.deletedString);
    }

    /**
     * Indicates whether this command should be saved in the undo history
     *
     * @return {@code true} if the command should be saved in history; {@code false} otherwise
     */
    public boolean toBeSavedInHistory() {
        return toHistory;
    }
}
