import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        String strInput = "";

        Stack<String> stack = new Stack<>();

        do {
            //this is AddCommand sample
            strInput = input.nextLine();
            stack.push(strInput);

            if (!Objects.equals(strInput, "q")) {
                // Create receiver
                Receiver receiver = new Receiver();

                // Create command objects
                Command addCommand = new AddCommand(receiver, strInput);

                // Create invoker
                Invoker invoker = new Invoker();

                // Set and execute commands
                invoker.setCommandsForExecution(addCommand);
                invoker.executeCommand(); // Outputs: TV is now on
            }
        } while (!strInput.toLowerCase().equals("q"));
    }
}