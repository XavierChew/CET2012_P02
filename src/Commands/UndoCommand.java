package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.Stack;

public class UndoCommand implements Command {
    /**
     * Variable for history stack
     */
    private Stack<Command> history;
    /**
     * Variable for receiver
     */
    private Receiver receiver;

    /**
     * Constructor of Delete Command
     * @param receiver the receiver
     * @param history the history stack
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Execute method
     */
    @Override
    public void execute() {
        if (this.receiver == null) {
            throw new CustomException("Receiver cannot be null.");
        }
        if (history.isEmpty()) {
            System.out.println("Undo not available.");
        }
        Command lastAction = history.pop();
        lastAction.undo();
        System.out.println("Undo");
    }

    /**
     * Undo method
     */
    @Override
    public void undo() {}

    /**
     * A method to decide if this command need to save in history
     * For Undo, we do not need to save in history
     * @return false
     */
    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}