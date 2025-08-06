package Commands;

import Tools.Receiver;

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
    //private String strListCommand;

    /**
     * Constructor of Delete Command
     * @param receiver the receiver
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
        //this.strListCommand = strListCommand;
    }

    /**
     * Execute method
     */
    @Override
    public void execute(){
        receiver.list();
    }
    @Override
    public void undo(){

    }
    @Override
    public boolean toBeSavedInHistory() {
        return false;

    }

}
