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
    private boolean toHistory = true;

    public DeleteCommand(Receiver receiver, String strDeleteCommand) {
        this.receiver = receiver;
        this.strDeleteCommand = strDeleteCommand;
    }

    public void execute() {
        if (this.strDeleteCommand == null) {
            throw new CustomException("command cannot be null.");
        }

        String[] splitDeleteCommand = this.strDeleteCommand.split("\\s+");

        if (receiver.dataStorage.isEmpty()) {
            toHistory = false;
            throw new CustomException("Nothing to delete.");
        }
        //check command format
        if (splitDeleteCommand.length != 1 ) {
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        //check index
        String strIndexPattern = "\"^[1-9]\\\\d*$\"";

        boolean found = false;

        // creating the Pattern & Matcher object
        Pattern pattern = Pattern.compile(strIndexPattern);
        Matcher matcher = pattern.matcher(this.strDeleteCommand);

        // the search
        while (matcher.find()) {
            found = true;
        }

        if (!found){
            toHistory = false;
            throw new CustomException("Invalid command");
        }

        this.deletedIndex = Integer.parseInt(this.strDeleteCommand) - 1;
        this.deletedString = this.receiver.get(this.deletedIndex);
        this.receiver.delete(this.deletedIndex);
    }


    public void undo() {
        if (this.deletedString == null) {
            toHistory = false;
            throw new CustomException("Error while performing undo.");
        }
        this.receiver.insert(this.deletedIndex, this.deletedString);
    }

    public boolean toBeSavedInHistory() {
        return toHistory;
    }
}