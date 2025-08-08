package Tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The {@code Receiver} class manages the core data storage for command operations.
 * It supports adding, updating, deleting, inserting, and listing entries.
 * It also handles loading from and saving to a persistent file (`dataStore.txt`).
 */
public class Receiver {
    /**
     * List to store data entries.
     */
    private ArrayList<String> dataStorage = new ArrayList<>();

    /**
     * Static variable to temporarily store a string for undoing an update.
     */
    static String strForUndoUpdate;

    /**
     * Constructs a new Receiver and loads existing data from the file if it exists.
     */
    public Receiver() {
        loadExistingData();
    }

    /**
     * Returns the number of data entries currently stored.
     *
     * @return the size of the data storage
     */
    public int getStorageSize() {
        return dataStorage.size();
    }

    /**
     * Adds a new data entry to the storage.
     *
     * @param strData the data string to add
     */
    public void add(String strData) {
        dataStorage.add(strData);
    }

    /**
     * Updates an existing entry at a specific index with new data.
     * Only updates fields that are provided in the update string.
     * Also stores the original entry for undo purposes.
     *
     * @param intUpdateIndex the index to update
     * @param strUpdateData  the new data to update
     * @param printMessage   flag to optionally print messages (not used here)
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

    /**
     * Retrieves the data entry at a given index.
     *
     * @param index the index of the entry
     * @return the data string at the specified index
     */
    public String get(int index) {
        return dataStorage.get(index);
    }

    /**
     * Deletes the data entry at the given index.
     *
     * @param intDeleteIndex the index to delete
     */
    public void delete(int intDeleteIndex) {
        dataStorage.remove(intDeleteIndex);
        System.out.println("Delete");
    }

    /**
     * Inserts data into a specific index in the data storage.
     *
     * @param index the index to insert the data at
     * @param data  the data string to insert
     */
    public void insert(int index, String data) {
        dataStorage.add(index, data);
    }

    /**
     * Lists all data entries in the storage, formatted with index numbers.
     * If the storage is empty, a message is printed instead.
     */
    public void list() {
        if (dataStorage.isEmpty()) {
            System.out.println("Data storage is empty");
        } else {
            System.out.println("List");
            int index = 1;
            for (String data : dataStorage) {
                String index_Print = String.format("%02d.", index++) + " " + data;
                System.out.println(index_Print);
            }
        }
    }

    /**
     * Loads existing data from the file into the data storage.
     * Skips empty lines.
     */
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
     * Saves all current data entries to the file `dataStore.txt`.
     * If the file does not exist, it is created.
     * The file is overwritten with the latest data.
     */
    public void storeToFile() {
        Path filepath = Paths.get("./src/dataStore.txt");
        File file = new File(filepath.toUri());

        try {
            // Create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Open in overwrite mode (truncate)
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
