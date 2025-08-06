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

    private String strForUndoUpdate = "";

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
        try {
            String[] splitUpdateCommand = this.strUpdateCommand.split("\\s+");

            //check command format
            if (splitUpdateCommand.length > 1) {
                throw new CustomException("Invalid command");
            }

            //check valid email
            String strEmailPattern = "([a-z0-9_.-]+)@([a-z0-9_.-]+[a-z])";

            boolean found = false;

            // creating the Pattern & Matcher object
            Pattern pattern = Pattern.compile(strEmailPattern);
            Matcher matcher = pattern.matcher(splitUpdateCommand[3]);

            // the search
            while (matcher.find()) {
                found = true;
            }

            if (!found)
                throw new CustomException("Invalid email");

            //get index, make it match with data storage index
            int intUpdateIndex = (Integer.parseInt(this.strUpdateCommand.split(" ")[0])) - 1;

            //get index after the 1st space
            int firstSpaceIndex = this.strUpdateCommand.indexOf(" ");

            //to store data to be updated
            String strUpdateData = "";
            if (firstSpaceIndex != -1) {
                strUpdateData = strUpdateCommand.substring(firstSpaceIndex + 1);
            }
            // Storing the original data for Undo.
            strForUndoUpdate = receiver.get(intUpdateIndex);

            receiver.update(intUpdateIndex, strUpdateData,true);
            System.out.println("Update");
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
    @Override
    public void undo(){
        int intUpdateIndex = (Integer.parseInt(this.strUpdateCommand.split(" ")[0])) - 1;
        receiver.update(intUpdateIndex, strForUndoUpdate,false);
    }

    @Override
    public boolean toBeSavedInHistory() {
        return true;
    }

}
