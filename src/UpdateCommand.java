import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Update Command class
 */
public class UpdateCommand implements Command {
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
    public UpdateCommand(Receiver receiver, String strInput) {
        this.receiver = receiver;
        this.strInput = strInput;
    }

    /**
     * Execute method
     */
    @Override
    public void execute() {
        String intLine = this.strInput.split(" ")[0];
        String strUpdateInput = this.strInput.replaceFirst(intLine,"");
        int count = 0;
        String strLine = "";
        Path filepath = Paths.get("./src/resources/payload.txt");

        if (Files.notExists(filepath)) {
            System.out.println("File does not exist");
            return;
        }

        try (InputStream in = Files.newInputStream(filepath);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(in))) {

            while ((strLine = reader.readLine()) != null) {
                count ++;
                if (count == Integer.parseInt(intLine)) {
                    BufferedWriter writer = Files.newBufferedWriter(filepath);
                    writer.write(strUpdateInput);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

//        try (SeekableByteChannel sbc = Files.newByteChannel(filepath,
//                StandardOpenOption.WRITE)) {
//            byte[] data = strUpdateInput.getBytes();
//            ByteBuffer bb = ByteBuffer.wrap(data);
//            sbc.write(bb);
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }

//        try (BufferedReader buff_read = Files.newBufferedReader(filepath)) {
//            // read the file contents from the buffer line by line
//            while ( (strLine = buff_read.readLine()) != null) {
//                count ++;
//                if (count == Integer.parseInt(intLine)) {
//                    BufferedWriter buff_writer = Files.newBufferedWriter(filepath);
//                    buff_writer.write(strUpdateInput);
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }

        this.receiver.update(this.strInput);
    }

}
