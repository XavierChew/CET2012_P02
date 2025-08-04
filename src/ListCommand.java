/**
 * List Command class
 */
public class ListCommand implements Command {
    /**
     * Variable for receiver
     */
    private Receiver receiver;
    /**
     * Variable for list command
     */
    private String strListCommand;

    /**
     * Constructor of Delete Command
     * @param receiver the receiver
     * @param strListCommand the list command
     */
    public ListCommand(Receiver receiver, String strListCommand) {
        this.receiver = receiver;
        this.strListCommand = strListCommand;
    }

    /**
     * Execute method
     */
    @Override
    public void execute(){
        receiver.list(this.strListCommand);
    }

}
