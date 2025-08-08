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
     * Variable to decide if need to store in history
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
        //straddcmd check null
        if (this.strAddCommand == null) {
            throw new CustomException("command cannot be null.");
        }

        String[] splitAddCommand = this.strAddCommand.split("\\s+");

        //check command format
        if (splitAddCommand.length != 3) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        //check valid email
        String strPattern = "^([a-zA-Z0-9_]+|[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})$";
        boolean found = false;

        // creating the Pattern & Matcher object
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(splitAddCommand[2]);

        // the search
        while (matcher.find()) {
            found = true;
        }

        // invalid email if not found
        if (!found){
            throw new CustomException("Invalid command.");
        }

        // to upper for the 1st char
        splitAddCommand[0] = splitAddCommand[0].substring(0,1).toUpperCase() + splitAddCommand[0].substring(1);
        splitAddCommand[1] = splitAddCommand[1].substring(0,1).toUpperCase() + splitAddCommand[1].substring(1);

        this.strAddCommand = String.join(" ", splitAddCommand);
        this.receiver.add(this.strAddCommand);
        System.out.println("Add");
    }

    /**
     * Undo method
     */
    @Override
    public void undo(){
        receiver.dataStorage.removeLast();
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