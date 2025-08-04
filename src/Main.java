import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * Driver class:
 * Allow user to key in data and based on format,
 * perform the command accordingly
*/
public class Main {

    public static void main(String[] args) {
        String strAddInput1 = "FirstName1 LastName1 email1"; //for add
        String strAddInput2 = "FirstName2 LastName2 email2"; //for add
        String strUpdateInput1 = "1 FirstNameChange1 Fakename"; //for update
        String strDeleteInput1 = "2";
        Stack<Command> history = new Stack<>(); //store command history

        // Create receiver
        Receiver receiver = new Receiver();

        Command addCommand1 = new AddCommand(receiver, strAddInput1);
        Command addCommand2 = new AddCommand(receiver, strAddInput2);
        Command updateCommand = new UpdateCommand(receiver, strUpdateInput1);
        Command deleteCommand = new DeleteCommand(receiver, strDeleteInput1);
        Command listCommand = new ListCommand(receiver);


        // Create command objects
        Command[] command = {addCommand1, addCommand2, updateCommand,deleteCommand, listCommand};

        // Create invoker
        Invoker invoker = new Invoker();

        // Set and execute commands
        invoker.setCommandsForExecution(command);
        invoker.executeCommand(history);

//        Scanner input = new Scanner(System.in);  // Create a Scanner object
//        String strInput; //store input
//        Stack<Command> history = new Stack<>(); //store command history
//        int counter = 0; //counter for array
//
//        System.out.println("To quit, enter \"q\".");
//
//        do {
//            System.out.println("Enter Command: ");
//            strInput = input.nextLine();
//
//            if (!Objects.equals(strInput.toLowerCase(), "q")) {
//                counter++;
//
//                // Create receiver
//                Receiver receiver = new Receiver();
//
//                //lazy solution, should use regex
//                int countSpace = 0;
//                for (int i = 0; i < strInput.length(); i++) {
//                    if (strInput.charAt(i) == ' ') {
//                        countSpace++;
//                    }
//                }
//
//                if (countSpace == 2) {
//                    Command addCommand = new AddCommand(receiver, strInput);
//                    history.add(addCommand);
//                }
//                //update is not working yet
//                else if (countSpace == 3) {
//                    Command updateCommand = new UpdateCommand(receiver, strInput);
//                    history.add(updateCommand);
//                }
//
//                // Create command objects
//                Command[] command = new Command[counter];
//                command = history.toArray(command);
//
//                // Create invoker
//                Invoker invoker = new Invoker();
//
//                // Set and execute commands
//                invoker.setCommandsForExecution(command);
//                invoker.executeCommand(history);
//            }
//        } while (!strInput.equalsIgnoreCase("q"));
    }
}