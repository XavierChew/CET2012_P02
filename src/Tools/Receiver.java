package Tools;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import static java.nio.file.Files.createFile;

/**
 * The {@code Receiver} class contains the actual logic that needs to be executed
 */
public class Receiver {
    /**
     * Variable of list to store data
     */
    private ArrayList<String> dataStorage = new ArrayList<>();

    /**
     * Variable of string for undoing an update
     */
    private String strForUndoUpdate;

    /**
     * Variable of indicator if it is from undo command
     */
    private boolean isUndo;

    /**
     * Constructs a new Receiver and loads existing data from the file if it exists
     */
    public Receiver() {
        Path filepath = Paths.get("./src/dataStore.txt");
        String line = "";

        if (Files.notExists(filepath)) {
            System.out.println("File does not exist");
            return;
        }

        try (BufferedReader buff_read = Files.newBufferedReader(filepath)) {
            while ( (line = buff_read.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    dataStorage.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    /**
     * To get the size of the data storage
     *
     * @return the size of the data storage
     */
    public int getStorageSize() {
        return dataStorage.size();
    }

    /**
     * Retrieves the data entry at a given index
     *
     * @param index the index of the entry
     * @return the data string at the specified index
     */
    public String get(int index) {
        return dataStorage.get(index);
    }

    /**
     * To get if it is from UndoCommand
     *
     * @return {@code true} if the command is from undo and {@code false} otherwise
     */
    public boolean setUndo(boolean isUndo) {
        return isUndo;
    }

    /**
     * To get if it is from UndoCommand
     *
     * @return {@code true} if the command is from undo and {@code false} otherwise
     */
    public boolean getUndo() {
        return this.isUndo;
    }

    /**
     * Inserts data into a specific index in the data storage
     *
     * @param index the index to insert the data at
     * @param data  the data string to insert
     */
    public void insert(int index, String data) {

        dataStorage.add(index, data);
    }

    /**
     * Adds a new data to the storage
     *
     * @param strData the data string to add
     */
    public void add(String strData) {
        dataStorage.add(strData);
    }

    /**
     * Updates an existing entry at a specific index with new data
     * Only updates fields that are provided in the string
     * Also stores the original entry for undo purposes
     *
     * @param intUpdateIndex the index to update
     * @param strUpdateData  the new data to update
     */
    public void update(int intUpdateIndex, String strUpdateData) {
        String original = dataStorage.get(intUpdateIndex);
        strForUndoUpdate = original; //for undo
        String[] arrOrigin = original.split("\\s+");
        String[] arrUpdate = strUpdateData.split("\\s+");

        for (int i = 0; i < arrUpdate.length; ++i) {
            arrOrigin[i] = arrUpdate[i];
        }

        String updatedInput = arrOrigin[0] + " " + arrOrigin[1] + " " + arrOrigin[2];
        dataStorage.set(intUpdateIndex, updatedInput);
    }

    /**
     * Deletes the data
     *
     * @param intDeleteIndex the index to delete
     */
    public void delete(int intDeleteIndex) {
        dataStorage.remove(intDeleteIndex);
    }

    /**
     * Lists all data in the storage, formatted with index numbers
     * If the storage is empty, a message is printed
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
     * Saves all current data from storage to the file `dataStore.txt`.
     * Create new file if the file does not exist
     * The file is overwritten with the latest data
     */
    public void storeToFile() {
        Path filepath = Paths.get("./src/dataStore.txt");
        String line = "";

        if (Files.notExists(filepath)) {
            try {
                createFile(filepath);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        try (BufferedWriter buff_writer = Files.newBufferedWriter(filepath)) {
            for (String strData : dataStorage) {
                buff_writer.write(strData);
                buff_writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error write to file: " + e.getMessage());
        }
    }
}
