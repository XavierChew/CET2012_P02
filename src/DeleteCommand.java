/**
 * Delete Command class
 */
public class DeleteCommand implements Command {
    /**
     * Variable for receiver
     */
    private Receiver receiver;
    /**
     * Variable for delete command
     */
    private String strDeleteCommand;

    /**
     * Constructor of Delete Command
     * @param receiver the receiver
     * @param strDeleteCommand the delete command
     */
    public DeleteCommand(Receiver receiver, String strDeleteCommand) {
        this.receiver = receiver;
        this.strDeleteCommand = strDeleteCommand;
    }

    /**
     * Execute method
     */
    @Override
    public void execute(){
        //pass in index which match with data storage index
        receiver.delete(Integer.parseInt(this.strDeleteCommand.split(" ")[0]) - 1);
    }

}
