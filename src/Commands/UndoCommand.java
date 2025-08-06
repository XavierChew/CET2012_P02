package Commands;

import Exception.CustomException;
import Tools.Receiver;
import java.util.Stack;

public class UndoCommand implements Command {

    //private final String strUndoCommand;
    private Stack<Command> history;
    private Receiver receiver;

    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        //this.strUndoCommand = strUndoCommand;
        this.history = history;
    }

    @Override
    public void execute() {
        Command lastAction = history.pop();
        lastAction.undo();
        System.out.println("Undo");
    }

    @Override
    public void  undo() {}

    @Override
    public boolean toBeSavedInHistory() {
        return false;
    }
}

