package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.Stack;

/**
 * UndoCommand class that implements the Command interface.
 * This command undoes the last command stored in the history stack.
 */
public class UndoCommand implements Command {
    /**
     * Stack to maintain the history of executed commands.
     */
    private Stack<Command> history;

    /**
     * The receiver that performs the actual operations.
     */
    private Receiver receiver;

    /**
     * Constructor for UndoCommand.
     *
     * @param receiver the receiver object used to perform operations
     * @param history  the command history stack
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Executes the UndoCommand by popping the last command from history
     * and calling its undo() method.
     *
     * @throws CustomException if the receiver is null or history is empty
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
     * Undo method for UndoCommand.
     * This method is intentionally left empty because UndoCommand itself does not need to be undone.
     */
    @Override
    public void undo() {}

    /**
     * Indicates whether this command should be saved in history.
     * For UndoCommand, it always returns false.
     *
     * @return false since undo actions should not be stored in history
     */
    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}
