import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddCommand implements Command {
    /**
     * Variable for receiver
     */
    private Receiver receiver;
    /**
     * Variable for input
     */
    private String strInput;

    /**
     * Constructor of Update Command
     * @param receiver the receiver
     * @param strInput the input
     */
    public AddCommand(Receiver receiver, String strInput) {
        this.receiver = receiver;
        this.strInput = strInput;
    }

    /**
     * Execute method
     */
    @Override
    public void execute() {
//        Path filepath = Paths.get("./src/resources/payload.txt");
//
//        if (Files.notExists(filepath)) {
//            System.out.println("File does not exist");
//            return;
//        }
//        this.strInput = this.strInput + "\n";
//        try (SeekableByteChannel sbc = Files.newByteChannel(filepath,
//                StandardOpenOption.APPEND)) {
//            byte[] data = this.strInput.getBytes();
//            ByteBuffer bb = ByteBuffer.wrap(data);
//            sbc.write(bb);
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }

        this.receiver.add(this.strInput);
    }

}
