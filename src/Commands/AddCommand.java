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
    private boolean hasError = false;

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
            String strEmailPattern;

//check command format

            for (int i = 0; i < splitAddCommand.length; i++) {
                if (i == 0 || i == 1) {
                    strEmailPattern = "[a-zA-Z0-9_]";
                }
                else {
                    strEmailPattern = "^([a-zA-Z0-9_]+|[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})$";
                }

                boolean found = false;

                // creating the Pattern & Matcher object
                Pattern pattern = Pattern.compile(strEmailPattern);
                Matcher matcher = pattern.matcher(splitAddCommand[i]);

                // the search
                while (matcher.find()) {
                    found = true;
                }

                if (!found && i != 2)
                    throw new CustomException("Invalid command/email");
                    hasError = true;

            }

            this.receiver.add(this.strAddCommand);
            System.out.println("Add");
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
            hasError = true;
        }
    }

    @Override
    public void undo(){
        Receiver.dataStorage.removeLast();
    }

    @Override
    public boolean toBeSavedInHistory() {
        return !hasError;
    }
}
