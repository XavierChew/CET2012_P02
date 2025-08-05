import java.util.Stack;

/**
 * Create and configure the command objects
*/
public class Main {

    public static void main(String[] args) {
        String strAddCommand1 = "FirstName1 LastName1 email1";
        String strAddCommand2 = "2ndName 2ndlast email2";
        String strAddCommand3 = "3rdName 3rdlast email3";
        String strUpdateCommand = "1 FirstNameChange1";
        String strDeleteCommand = "1"; //for delete
        String strUndoCommand = "Undo"; //for undo
        String strListCommand = "List"; //for list
        Stack<Command> history = new Stack<>(); //history stack


        // Create receiver
        Receiver receiver = new Receiver();

        //might need to review this to make it more automatic start
        // Create command objects
        Command addCommand = new AddCommand(receiver, strAddCommand1);
        Command addCommand1 = new AddCommand(receiver, strAddCommand2);
        Command addCommand2 = new AddCommand(receiver, strAddCommand3);
        Command updateCommand = new UpdateCommand(receiver, strUpdateCommand);
        Command deleteCommand = new DeleteCommand(receiver, strDeleteCommand);
        Command undoCommand = new UndoCommand(receiver, strUndoCommand,history);
        Command listCommand = new ListCommand(receiver, strListCommand);

        // Store to command objects array
//        Command[] cmdToExecute = {addCommand, updateCommand, listCommand};
        Command[] cmdToExecute = {addCommand, addCommand1,undoCommand, undoCommand, addCommand2, listCommand};
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