package Tools;

import java.io.*;
import java.nio.file.Files;
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
    private ArrayList<String> dataStorage = new ArrayList<>();

    /**
     * Variable for storing data for undo Update
     */
    static String strForUndoUpdate;
    //static String strForUndoDelete;

    /**
     * Receiver constructor
     */
    public Receiver() {
        loadExistingData();
    }

    /**
     * Get storage size
     */
    public int getStorageSize() {
        return dataStorage.size();
    }

    /**
     * Add function for Add Command
     *
     * @param strData Data
     */
    public void add(String strData) {
        dataStorage.add(strData);
    }

    /**
     * Update function for Update Command
     *
     * @param intUpdateIndex Index to be updated
     * @param strUpdateData  Data to be updated
     */
    public void update(int intUpdateIndex, String strUpdateData, boolean printMessage) {

        String original = dataStorage.get(intUpdateIndex);
        String[] arrOrigin = original.split("\\s+", 3);
        String[] arrUpdate = strUpdateData.split("\\s+", 3);

        for (int i = 0; i < arrUpdate.length; ++i) {
            arrOrigin[i] = arrUpdate[i];
        }

        String updatedInput = arrOrigin[0] + " " + arrOrigin[1] + " " + arrOrigin[2];
        strForUndoUpdate = original;
        dataStorage.set(intUpdateIndex, updatedInput);
    }


    public String get(int index) {
        return (String)dataStorage.get(index);
    }

    public void delete(int intDeleteIndex) {
        //storage size
        dataStorage.remove(intDeleteIndex);
        System.out.println("Delete");
    }

    public void insert(int index, String data) {
        dataStorage.add(index, data);
    }

    public void list() {
        if (dataStorage.isEmpty()) {
            System.out.println("Data storage is empty");
        }
        else {System.out.println("List");
            int index = 1;

            for(String data : dataStorage) {
                String index_Print = String.format("%02d.", index++) + " " + data;
                System.out.println(index_Print);
            }
        }

    }

    public void loadExistingData() {
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
    }
}