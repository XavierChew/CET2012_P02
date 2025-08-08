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
        //testCase();
    }

    public static void testCase() {
        Stack<Command> history = new Stack<Command>();
        Receiver receiver = new Receiver();

        Command[] cmdlist = {
                new AddCommand(receiver,"1 %2FTnDD t3_4@edd.com"),
                new AddCommand(receiver,"First_name Last_name email"),
                new UpdateCommand(receiver,"1 2"),
                new UndoCommand(receiver, history),
                new DeleteCommand(receiver,"999"),
                new UndoCommand(receiver, history),
                new ListCommand(receiver)
        };
        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(cmdlist);
        invoker.executeCommand(history);

        receiver.storeToFile();
    }
}
