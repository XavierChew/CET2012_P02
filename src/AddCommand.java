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
        this.receiver.add(this.strAddCommand);
    }

}
