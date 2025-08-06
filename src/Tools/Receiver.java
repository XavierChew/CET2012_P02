package Tools;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Receiver class:
 */
public class Receiver {

    /**
     * Variable for dataStorage
     */
    public static ArrayList<String> dataStorage = new ArrayList<>();
    static String strForUndoDelete;
    static String strForUndoUpdate;
    /**
     * Variable for dataStorage
     */

    public Receiver() {
        loadExistingFile();
    }

    /**
     * Add function for Add Command
     *
     * @param strData Data
     */
    public void add(String strData) {
        dataStorage.add(strData);
        //System.out.println("Add");
        //System.out.println("Add " + strData); //for testing
    }

    /**
     * Update function for Update Command
     *
     * @param intUpdateIndex Index to be updated
     * @param strUpdateData  Data to be updated
     */
    public void update(int intUpdateIndex, String strUpdateData, boolean printMessage) {
        String original = (String)dataStorage.get(intUpdateIndex);
        String[] arrOrigin = original.split("\\s+", 3);
        String[] arrUpdate = strUpdateData.split("\\s+", 3);

        for(int i = 0; i < arrUpdate.length; ++i) {
            arrOrigin[i] = arrUpdate[i];
        }

        String updatedInput = arrOrigin[0] + " " + arrOrigin[1] + " " + arrOrigin[2];
        strForUndoUpdate = original;
        dataStorage.set(intUpdateIndex, updatedInput);
        if (printMessage) {
            System.out.println("Update");
        }

    }

    public String get(int index) {
        return (String)dataStorage.get(index);
    }

    public void delete(int intDeleteIndex) {
        dataStorage.remove(intDeleteIndex);
        System.out.println("Delete");
    }

    public void insert(int index, String data) {
        dataStorage.add(index, data);
    }

    public void list() {
        System.out.println("list");
        int index = 1;
        for(String data : dataStorage) {

            String index_Print = String.format("%02d.", index++) + " " + data;
            System.out.println(index_Print);
        }

    }

    private void loadExistingFile() {
        Path filepath = Paths.get("./src/dataStore.txt");
        File file = new File(filepath.toUri());

        if (!file.exists()) {
            return; // Nothing to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    dataStorage.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    /**
     * Store data to file
     */
    public void storeToFile() {
        Path filepath = Paths.get("./src/dataStore.txt");
        File file = new File(filepath.toUri());

        try {
            // Create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Open in append mode
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));

            for (String strData : dataStorage) {
                bufferedWriter.write(strData);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }

//        Path filepath = Paths.get("./src/dataStore.txt");
//        File file = new File(filepath.toUri());
//
//
//
//        try (BufferedWriter buff_writer = Files.newBufferedWriter(filepath)) {
//            if (!file.exists()) {
//               file.createNewFile();
//
//                for (String strData : this.dataStorage) {
//                    String data = strData+ "\n";
//                    buff_writer.write(data);
//                }
//            }
//            else{
//                try (OutputStream out = Files.newOutputStream(filepath, StandardOpenOption.APPEND);
//                     BufferedOutputStream buff_out= new BufferedOutputStream(out)) {
//
//                    for (String strData : this.dataStorage) {
//                        String data = strData+ "\n";
//                        buff_writer.write(data);
//                    }
//                } catch (IOException x) {
//                    System.err.println(x);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }

//        Path filepath = Paths.get("./src/dataStore.txt");
//        File file = new File(filepath.toUri());
//
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileWriter fr = new FileWriter(file, true);
//            BufferedWriter br = new BufferedWriter(fr);
//            PrintWriter pr = new PrintWriter(br);
//            for (String strData : this.dataStorage) {
//                pr.println(strData);
//            }
//            pr.close();
//            br.close();
//            fr.close();
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
    }

}




