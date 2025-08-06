package Commands;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Commands.Command;
import Tools.Receiver;

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
            this.deletedIndex = Integer.parseInt(this.strDeleteCommand) - 1;
            this.deletedString = this.receiver.get(this.deletedIndex);
            this.receiver.delete(this.deletedIndex);
        } catch (Exception e) {
            System.out.println("Error in DeleteCommand execute(): " + e.getMessage());
        }

    }

    public void undo() {
        if (this.deletedString != null) {
            this.receiver.insert(this.deletedIndex, this.deletedString);
        }

    }

    public boolean toBeSavedInHistory() {
        return true;
    }
}
