package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code DeleteCommand} class implements the {@link Command} interface
 * to provide functionality for deleting an item from the {@link Receiver}'s data storage.
 * It also supports undoing the delete operation by storing the deleted item and its index.
 */
public class DeleteCommand implements Command {
    /**
     * The receiver that performs the actual delete operation.
     */
    private Receiver receiver;

    /**
     * The string containing the delete command (index).
     */
    private String strDeleteCommand;

    /**
     * The string that was deleted, used for undoing.
     */
    private String deletedString = "";

    /**
     * The index from which the string was deleted.
     */
    private int deletedIndex;

    /**
     * A flag to determine whether this command should be added to history.
     */
    private boolean toHistory = true;

    /**
     * Constructs a {@code DeleteCommand} with the specified receiver and command string.
     *
     * @param receiver          the {@link Receiver} instance
     * @param strDeleteCommand  the command input representing the index to delete
     */
    public DeleteCommand(Receiver receiver, String strDeleteCommand) {
        this.receiver = receiver;
        this.strDeleteCommand = strDeleteCommand;
    }

    /**
     * Executes the delete command.
     * Validates the input and deletes the item at the specified index.
     *
     * @throws CustomException if the receiver is null, command is invalid, or index is out of bounds
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
     * Undoes the delete operation by reinserting the deleted string at its original index.
     *
     * @throws CustomException if the deleted string is null
     */
    public void undo() {
        if (this.deletedString == null) {
            toHistory = false;
            throw new CustomException("Error while performing undo.");
        }
        this.receiver.insert(this.deletedIndex, this.deletedString);
    }

    /**
     * Indicates whether this command should be saved in the undo history.
     *
     * @return {@code true} if the command should be saved in history; {@code false} otherwise
     */
    public boolean toBeSavedInHistory() {
        return toHistory;
    }
}
