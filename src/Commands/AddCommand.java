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
    private String strForUndoAdd = "";

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
    public void execute() {
        try {
            String[] splitAddCommand = this.strAddCommand.split("\\s+");

            //check command format
            if (splitAddCommand.length != 3) {
                throw new CustomException("Invalid command");
            }

            //check valid email
            String strEmailPattern = "([a-z0-9_.-]+)@([a-z0-9_.-]+[a-z])";

            boolean found = false;

            // creating the Pattern & Matcher object
            Pattern pattern = Pattern.compile(strEmailPattern);
            Matcher matcher = pattern.matcher(splitAddCommand[2]);

            // the search
            while (matcher.find()) {
                found = true;
            }

            if (!found)
                throw new CustomException("Invalid email");

            this.receiver.add(this.strAddCommand);
            System.out.println("Add");
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void undo(){
        Receiver.dataStorage.removeLast();
    }

    @Override
    public boolean toBeSavedInHistory() {
        return true;
    }
}
