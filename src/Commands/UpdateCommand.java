package Commands;

import Tools.Receiver;
import Exception.CustomException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to update a data entry in the receiver.
 * Validates command format, index, and optionally the format of the data fields (Latin or email).
 */
public class UpdateCommand implements Command {
    /**
     * Variable of the receiver that performs the actual listing operation
     */
    private Receiver receiver;

    /**
     * Variable of the update command
     */
    private String strUpdateCommand;

    /**
     * Variable of string for undoing update
     */
    private String strForUndoUpdate = "";

    /**
     * Variable of the flag to determine if the command should be saved in history
     */
    private boolean toHistory = true;

    /**
     * Constructor for UpdateCommand
     *
     * @param receiver the receiver that performs the command
     * @param strUpdateCommand the command string to be executed
     */
    public UpdateCommand(Receiver receiver, String strUpdateCommand) {
        this.receiver = receiver;
        this.strUpdateCommand = strUpdateCommand;
    }

    /**
     * Executes the update command
     */
    @Override
    public void execute() {
        if (this.receiver == null) {
            toHistory = false;
            throw new CustomException("Receiver cannot be null.");
        }

        if (this.strUpdateCommand == null ||  this.strUpdateCommand.isEmpty()) {
            toHistory = false;
            throw new CustomException("command cannot be null.");
        }
        if (receiver.getStorageSize()<=0){
            toHistory = false;
            throw new CustomException("Storage size is zero, can't update command.");
        }

        String[] splitUpdateCommand = this.strUpdateCommand.split("\\s+");

        if (splitUpdateCommand.length < 2 || splitUpdateCommand.length > 4) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        // Validate index
        int intUpdateIndex = -1;

        try {
            intUpdateIndex = Integer.parseInt(splitUpdateCommand[0]) - 1;
        }
        catch (Exception e) {
            throw new CustomException("Invalid index to update");
        }
        if(intUpdateIndex < 0) {
            throw new CustomException("Index cannot be less than 0");
        }
        if(intUpdateIndex >= receiver.getStorageSize()) {
            throw new CustomException("Index position exceed data size");
        }

        // Define regex for validation
        String localEmailPattern = "(?!.*[.-]{2})(?!.*[_.-][.-])(?!.*[.-][_.-])[a-zA-Z0-9](?:[a-zA-Z0-9_]|(?<![._-])[.-](?![._-]))*[a-zA-Z0-9]";
        String domainEmailPattern = "(?!.*[.-]{2})(?!.*[.-][.-])(?!.*[.-][.-])[a-zA-Z0-9](?:[a-zA-Z0-9]|(?<![.-])[.-](?![.-]))*[a-zA-Z0-9]";
        String fullEmailPattern = "^" + localEmailPattern + "@" + domainEmailPattern + "$";
        String latinPattern = "^[a-zA-Z0-9_]+$";

        // Validate last field (if exists)
        if (splitUpdateCommand.length == 4) {
            String field4 = splitUpdateCommand[3];
            boolean isValidEmail = Pattern.matches(fullEmailPattern, field4);
            boolean isValidLatin = Pattern.matches(latinPattern, field4);

            if (!isValidEmail && !isValidLatin) {
                toHistory = false;
                throw new CustomException("Invalid format for third field. Must be a valid email or Latin string.");
            }
        }

        // Capitalize fields (excluding index)
        for (int i = 1; i < splitUpdateCommand.length; i++) {
            splitUpdateCommand[i] = splitUpdateCommand[i].substring(0,1).toUpperCase()
                    + splitUpdateCommand[i].substring(1).toLowerCase();
        }

        this.strUpdateCommand = String.join(" ", splitUpdateCommand);

        // Get string after index for update
        int firstSpaceIndex = this.strUpdateCommand.indexOf(" ");
        String strUpdateData = "";
        if (firstSpaceIndex != -1) {
            strUpdateData = this.strUpdateCommand.substring(firstSpaceIndex + 1);
        }

        // Save previous state for undo
        strForUndoUpdate = receiver.get(intUpdateIndex);

        // Perform update
        receiver.update(intUpdateIndex, strUpdateData);
        System.out.println("Update");
    }

    /**
     * Undoes the update command by restoring the original data at the updated index
     */
    @Override
    public void undo() {
        int intUpdateIndex = (Integer.parseInt(this.strUpdateCommand.split(" ")[0])) - 1;
        receiver.update(intUpdateIndex, strForUndoUpdate);
    }

    /**
     * Indicates whether this command should be saved in history
     *
     * @return {@code true} if the command should be saved; {@code false} otherwise
     */
    @Override
    public boolean toBeSavedInHistory() {
        return toHistory;
    }
}
