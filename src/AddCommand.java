import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Add Command class
 */
public class AddCommand implements Command {
    /**
     * Variable for receiver
     */
    private Receiver receiver;
    /**
     * Variable for add command
     */
    private String strAddCommand;
    private String strForUndoAdd = "";


    /**
     * Constructor of Update Command
     * @param receiver the receiver
     * @param strAddCommand the add command
     */
    public AddCommand(Receiver receiver, String strAddCommand) {
//        String[] splitCommand = strAddCommand.split("\\s+"); //format [FirstName, LastName, email]
//        String strPattern = "[a?&&a*]";
//
//        boolean found = false;
//
//        // creating the Pattern & Matcher object
//        Pattern pattern = Pattern.compile(strPattern);
//        Matcher matcher = pattern.matcher(strTextEx8);
//
//        // the search
//        while (matcherEx8.find()) {
//            System.out.printf("Text \"%s\" found at index %d to %d.\n",
//                    matcherEx8.group(), matcherEx8.start(), matcherEx8.end());
//            foundEx8 = true;
//        }
//
//        if (!foundEx8)
//            System.out.println("Nothing found.");
//        this.receiver = receiver;
//
//        if (splitCommand.length != 3) {
//            System.out.println("Invalid command format");
//        }
//        for (int i = 0; i < splitCommand.length; i++) {
//            if (splitCommand[i] )
//        }
//        if (splitCommand.length != 3) {
//            System.out.println("Invalid command format");
//        }
        this.receiver = receiver;
        this.strAddCommand = strAddCommand;
    }

    /**
     * Execute method
     */
    @Override
    public void execute() {
        this.receiver.add(this.strAddCommand);
    }

    @Override
    public void undo(){
        Receiver.dataStorage.removeLast();

    }
}
