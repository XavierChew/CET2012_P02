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
        //get index, make it match with data storage index
        int intUpdateIndex = (Integer.parseInt(this.strUpdateCommand.split(" ")[0])) - 1;

        //get index after the 1st space
        int firstSpaceIndex = this.strUpdateCommand.indexOf(" ");

        //to store data to be updated
        String strUpdateData = "";
        if (firstSpaceIndex != -1) {
            strUpdateData = strUpdateCommand.substring(firstSpaceIndex + 1);
        }

        receiver.update(intUpdateIndex, strUpdateData);
    }
    @Override
    public void undo(){}

}
