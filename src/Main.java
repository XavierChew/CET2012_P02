import java.util.Stack;

/**
 * Create and configure the command objects
*/
public class Main {

    public static void main(String[] args) {
        String strAddCommand = "FirstName1 LastName1 email1"; //for add
        String strUpdateCommand = "1 FirstNameChange1"; //for update
        String strDeleteCommand = "1"; //for delete
        //String strUndoCommand = "Undo"; //for undo
        String strListCommand = "List"; //for list
        Stack<Command> history = new Stack<>(); //history stack

        // Create receiver
        Receiver receiver = new Receiver();

        //might need to review this to make it more automatic start
        // Create command objects
        Command addCommand = new AddCommand(receiver, strAddCommand);
        Command updateCommand = new UpdateCommand(receiver, strUpdateCommand);
        Command deleteCommand = new DeleteCommand(receiver, strDeleteCommand);
        //Command undoCommand = new UndoCommand(receiver, strUndoCommand);
        Command listCommand = new ListCommand(receiver, strListCommand);

        // Store to command objects array
        Command[] cmdToExecute = {addCommand, updateCommand, deleteCommand, listCommand};
        //Command[] cmdToExecute = {addCommand, updateCommand,deleteCommand, listCommand, undoCommand, listCommand};
        //might need to review this to make it more automatic end

        // Create invoker
        Invoker invoker = new Invoker();

        // Set and execute commands
        invoker.setCommandsForExecution(cmdToExecute);
        invoker.executeCommand(history);

        // Store to file
        receiver.storeToFile();
    }
}