import Commands.*;
import Tools.Invoker;
import Tools.Receiver;

import java.util.Stack;

/**
 * Create and configure the command objects
*/
public class Main {

    public static void main(String[] args) {
        testCase();
        testCase();

    }

    public static void testCase(){
        Stack<Command> CommandStackHistory = new Stack<Command>();
        Receiver receiver = new Receiver();
        Command[] cmdlist = {
//                new AddCommand(receiver,"first_name Last_name Email"),
                new AddCommand(receiver,null),
                new ListCommand(receiver),
        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(cmdlist);
        invoker.executeCommand(CommandStackHistory);

    }

//    public static void main(String[] args) {
//
//        String strAddCommand1 = "firstName1 lastName1 email1";
//        String strAddCommand2 = "secondName 2ndlast email2";
//        String strAddCommand3 = "3rdName 3rdlast email3";
//        String strUpdateCommand = "1 FirstNameChange1";
//        String strUpdateCommand2 = "2 FirstNameChange2 lastname2";
//        String strDeleteCommand = "1"; //for delete
//
//        Stack<Command> history = new Stack<>(); //history stack
//
//        // Create receiver
//        Receiver receiver = new Receiver();
//
//        //might need to review this to make it more automatic start
//        // Create command objects
//        Command addCommand = new AddCommand(receiver, strAddCommand1);
//        Command addCommand1 = new AddCommand(receiver, strAddCommand2);
//        Command addCommand2 = new AddCommand(receiver, strAddCommand3);
//        Command updateCommand = new UpdateCommand(receiver, strUpdateCommand);
//        Command updateCommand2 = new UpdateCommand(receiver, strUpdateCommand2);
//        Command deleteCommand = new DeleteCommand(receiver, strDeleteCommand);
//        Command deleteCommand2 = new DeleteCommand(receiver, strDeleteCommand);
//        Command undoCommand = new UndoCommand(receiver, history);
//        Command listCommand = new ListCommand(receiver);
//
//        // Store to command objects array
////        Command[] cmdToExecute = {addCommand, updateCommand, listCommand};
//        Command[] cmdToExecute = {addCommand1, addCommand2, updateCommand, deleteCommand, undoCommand, listCommand};
//        Invoker invoker = new Invoker();
//
//        // Set and execute commands
//        invoker.setCommandsForExecution(cmdToExecute);
//        invoker.executeCommand(history);
//
//        // Store to file
//        receiver.storeToFile();
//    }
}