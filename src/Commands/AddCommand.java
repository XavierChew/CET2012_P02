package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Add Command class
 */
public class AddCommand implements Command {
    /**
     * Variable for receiver
     */
    private Receiver receiver;
    /**
     * Variable for add command
     */
    private String strAddCommand;
    /**
     * Variable to decide if addCommand need to be stored in history
     */
    private boolean toHistory = true;

    /**
     * Constructor of Update Command
     * @param receiver the receiver
     * @param strAddCommand the add command
     */
    public AddCommand(Receiver receiver, String strAddCommand) {
        this.receiver = receiver;
        this.strAddCommand = strAddCommand;
    }

    /**
     * Execute method
     */
    @Override
    public void execute(){

        //check null receiver
        if (this.receiver == null) {
            toHistory = false;
            throw new CustomException("Receiver cannot be null.");
        }

        //check null
        if (this.strAddCommand == null || this.strAddCommand.isEmpty()) {
            toHistory = false;
            throw new CustomException("command cannot be null.");
        }

        String[] splitAddCommand = this.strAddCommand.split("\\s+");

        //check command format
        if (splitAddCommand.length != 3) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        //Patterns
        String emailPattern = "^([a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})$";
        String latinPattern = "^[a-zA-Z0-9_]+$";

        //Field 3 Validation
        String field3 = splitAddCommand[2];
        boolean isValidEmail = Pattern.matches(emailPattern, field3);
        boolean isValidLatin = Pattern.matches(latinPattern, field3);

        if (!isValidEmail && !isValidLatin) {
            toHistory = false;
            throw new CustomException("Invalid format for third field. Must be a valid email or Latin string.");
        }

        // Title case for field 3 only if it's a Latin string (not an email)
        if (isValidLatin && !isValidEmail) {
            splitAddCommand[2] = splitAddCommand[2].substring(0,1).toUpperCase() + splitAddCommand[2].substring(1).toLowerCase();
        }

        // Title case for field 1 & 2
        splitAddCommand[0] = splitAddCommand[0].substring(0,1).toUpperCase() + splitAddCommand[0].substring(1).toLowerCase();
        splitAddCommand[1] = splitAddCommand[1].substring(0,1).toUpperCase() + splitAddCommand[1].substring(1).toLowerCase();


        this.strAddCommand = String.join(" ", splitAddCommand);
        this.receiver.add(this.strAddCommand);
        System.out.println("Add");
    }

    /**
     * Undo method
     */
    @Override
    public void undo(){
        int index = receiver.getStorageSize();
        receiver.delete(index - 1);
    }

    /**
     * A method to decide if this command need to save in history
     * @return toHistory
     */
    @Override
    public boolean toBeSavedInHistory() {
        return toHistory;
    }
}