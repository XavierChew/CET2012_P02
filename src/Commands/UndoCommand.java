package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.Stack;

/**
 * UndoCommand class undoes the last command stored in the history stack
 */
public class UndoCommand implements Command {
    /**
     * Variable of command history stack of executed commands
     */
    private Stack<Command> history;

    /**
     * Variable of the receiver that performs the actual listing operation
     */
    private Receiver receiver;

    /**
     * Constructor for UndoCommand
     *
     * @param receiver the receiver used to perform command
     * @param history  the command history stack
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Executes the undo command
     */
    @Override
    public void execute() {
        if (this.receiver == null) {
            throw new CustomException("Receiver cannot be null.");
        }
        if (history.isEmpty()) {
            throw new CustomException("History cannot be empty.");
        }
        Command lastAction = history.pop();
        lastAction.undo();
        System.out.println("Undo");
    }

    /**
     * Undo method for UndoCommand
     */
    @Override
    public void undo() {}

    /**
     * Indicates whether this command should be saved in history
     *
     * @return false since undo actions should not be stored in history
     */
    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}
