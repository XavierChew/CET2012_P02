package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code AddCommand} class add a new entry
 */
public class AddCommand implements Command {

    /**
     * Variable of the receiver that performs the actual listing operation
     */
    private Receiver receiver;

    /**
     * Variable of the add command
     */
    private String strAddCommand;

    /**
     * Variable of the flag indicating whether this command should be recorded in history.
     */
    private boolean toHistory = true;

    /**
     * Constructor for AddCommand
     *
     * @param receiver the receiver that performs the command
     * @param strAddCommand  the command string to be executed
     */
    public AddCommand(Receiver receiver, String strAddCommand) {
        this.receiver = receiver;
        this.strAddCommand = strAddCommand;
    }

    /**
     * Executes the add commands
     */
    @Override
    public void execute() {
        // check null receiver
        if (this.receiver == null) {
            toHistory = false;
            throw new CustomException("Receiver cannot be null.");
        }

        // check null or empty command
        if (this.strAddCommand == null || this.strAddCommand.isEmpty()) {
            toHistory = false;
            throw new CustomException("Command cannot be null.");
        }

        String[] splitAddCommand = this.strAddCommand.split("\\s+");

        // check command format
        if (splitAddCommand.length != 3) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        // Regex patterns
        String localEmailPattern = "(?!.*[.-]{2})(?!.*[_.-][.-])(?!.*[.-][_.-])[a-zA-Z0-9](?:[a-zA-Z0-9_]|(?<![._-])[.-](?![._-]))*[a-zA-Z0-9]";
        String domainEmailPattern = "(?!.*[.-]{2})(?!.*[.-][.-])[a-zA-Z0-9](?:[a-zA-Z0-9]|(?<![.-])[.-](?![.-]))*[a-zA-Z0-9]\\.[a-z]{2,3}";
        String fullEmailPattern = "^" + localEmailPattern + "@" + domainEmailPattern + "$";
        String latinPattern = "^[a-zA-Z0-9_]+$";

        // Field 3 validation
        String field3 = splitAddCommand[2];
        boolean isValidEmail = Pattern.matches(fullEmailPattern, field3);
        boolean isValidLatin = Pattern.matches(latinPattern, field3);

        if (!isValidEmail && !isValidLatin) {
            toHistory = false;
            throw new CustomException("Invalid format for third field. Must be a valid email or Latin string.");
        }

        // Title case field 3 only if it's a Latin string
        if (isValidLatin && !isValidEmail) {
            splitAddCommand[2] = splitAddCommand[2].substring(0, 1).toUpperCase()
                    + splitAddCommand[2].substring(1).toLowerCase();
        }

        // Title case for fields 1 and 2
        splitAddCommand[0] = splitAddCommand[0].substring(0, 1).toUpperCase()
                + splitAddCommand[0].substring(1).toLowerCase();
        splitAddCommand[1] = splitAddCommand[1].substring(0, 1).toUpperCase()
                + splitAddCommand[1].substring(1).toLowerCase();

        this.strAddCommand = String.join(" ", splitAddCommand);
        this.receiver.add(this.strAddCommand);
        System.out.println("Add");
    }

    /**
     * Undoes the add command by deleting the most recently added entry from the data storage
     */
    @Override
    public void undo() {
        int index = receiver.getStorageSize();
        receiver.delete(index - 1);
    }

    /**
     * Returns whether this command should be saved in the history stack.
     *
     * @return {@code true} if the command is valid and should be saved; {@code false} otherwise
     */
    @Override
    public boolean toBeSavedInHistory() {
        return toHistory;
    }
}
