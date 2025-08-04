public class DeleteCommand implements Command {

    private Receiver receiver;
    private String index;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public void execute(){
        receiver.delete(index);
    }

}
