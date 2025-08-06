package Commands;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Exception.CustomException;
import Commands.Command;
import Tools.Receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand implements Command {
    private Receiver receiver;
    private String strDeleteCommand;
    private String deletedString = "";
    private int deletedIndex;

    public DeleteCommand(Receiver receiver, String strDeleteCommand) {
        this.receiver = receiver;
        this.strDeleteCommand = strDeleteCommand;
    }

    public void execute() {
        try {
            String[] splitDeleteCommand = this.strDeleteCommand.split("\\s+");

            //check command format
            if (splitDeleteCommand.length > 0) {
                throw new CustomException("Invalid command");
            }

            //check index
            String strIndexPattern = "[0-9]";

            boolean found = false;

            // creating the Pattern & Matcher object
            Pattern pattern = Pattern.compile(strIndexPattern);
            Matcher matcher = pattern.matcher(this.strDeleteCommand);

            // the search
            while (matcher.find()) {
                found = true;
            }

            if (!found)
                throw new CustomException("Invalid index");

            this.deletedIndex = Integer.parseInt(this.strDeleteCommand) - 1;
            this.deletedString = this.receiver.get(this.deletedIndex);
            this.receiver.delete(this.deletedIndex);
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
            //System.out.println("Error in DeleteCommand execute(): " + e.getMessage());
        }
    }

    public void undo() {
        try {
            if (this.deletedString == null) {
                throw new CustomException("Error while performing undo.");
            }
            this.receiver.insert(this.deletedIndex, this.deletedString);
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean toBeSavedInHistory() {
        return true;
    }
}
