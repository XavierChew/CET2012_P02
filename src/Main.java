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

    }

    public static void testCase() {
        Stack<Command> history = new Stack<Command>();
        Receiver receiver = new Receiver();

        Command[] cmdlist = {
                new AddCommand(receiver,"JOhn Doe email"),
                new AddCommand(receiver,"First_name Last_name email"),
                new AddCommand(receiver,"^dhEIEI *daDAJSFN email"),
                new ListCommand(receiver),



        };
        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(cmdlist);
        invoker.executeCommand(history);

        receiver.storeToFile();

    }

    }
