package Commands;

import Tools.Receiver;
import Exception.CustomException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Update Command class
 */
public class UpdateCommand implements Command {
    /**
     * Variable for receiver
     */
    private Receiver receiver;
    /**
     * Variable for update command
     */
    private String strUpdateCommand;
    /**
     * Variable for storing previous data for undo purpose
     */
    private String strForUndoUpdate = "";
    /**
     * Variable to decide if need to store in history
     */
    private boolean toHistory = true;

    /**
     * Constructor of Update Command
     * @param receiver the receiver
     * @param strUpdateCommand the update command
     */
    public UpdateCommand(Receiver receiver, String strUpdateCommand) {
        this.receiver = receiver;
        this.strUpdateCommand = strUpdateCommand;
    }

    /**
     * Execute method
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
//        try {
        String[] splitUpdateCommand = this.strUpdateCommand.split("\\s+");

        // check command format
        if (splitUpdateCommand.length < 1 || splitUpdateCommand.length > 4) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        //check index
        String strIndexPattern = "^[1-9]\\d*$";

        boolean found = false;

        // creating the Pattern & Matcher object
        Pattern pattern = Pattern.compile(strIndexPattern);
        Matcher matcher = pattern.matcher(splitUpdateCommand[0]);

        // the search
        while (matcher.find()) {
            found = true;
        }

        if (!found){
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        // regex patterns
        String strPatterns = "^([a-zA-Z0-9_]+|[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})$";

        // validate input based on length
        if (splitUpdateCommand.length == 4) {
            Pattern pattern2 = Pattern.compile(strPatterns);
            Matcher matcher2 = pattern2.matcher(splitUpdateCommand[3]);
            if (!matcher2.matches()) {
                toHistory = false;
                throw new CustomException("Invalid command");
            }

        }
        // perform update
        for (int i = 1; i < splitUpdateCommand.length; i++) {
            splitUpdateCommand[i] = splitUpdateCommand[i].substring(0,1).toUpperCase() + splitUpdateCommand[i].substring(1).toLowerCase();
        }

        this.strUpdateCommand = String.join(" ", splitUpdateCommand);

        int intUpdateIndex = Integer.parseInt(splitUpdateCommand[0]) - 1;

        int firstSpaceIndex = this.strUpdateCommand.indexOf(" ");
        String strUpdateData = "";
        if (firstSpaceIndex != -1) {
            strUpdateData = this.strUpdateCommand.substring(firstSpaceIndex + 1);
        }

        // store for undo
        strForUndoUpdate = receiver.get(intUpdateIndex);

        receiver.update(intUpdateIndex, strUpdateData, true);
        System.out.println("Update");
    }

    /**
     * Undo method
     */
    @Override
    public void undo(){
        int intUpdateIndex = (Integer.parseInt(this.strUpdateCommand.split(" ")[0])) - 1;
        receiver.update(intUpdateIndex, strForUndoUpdate,false);
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