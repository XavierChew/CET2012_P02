package Commands;

import Exception.CustomException;
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
     * Constructor of Delete Command
     * @param receiver the receiver
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute method
     */
    @Override
    public void execute(){
        if (this.receiver == null) {
            throw new CustomException("Receiver cannot be null.");
        }
        receiver.list();
    }

    /**
     * Undo method
     */
    @Override
    public void undo(){}

    /**
     * A method to decide if this command need to save in history
     * For List, we do not need to save in history
     * @return false
     */
    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}