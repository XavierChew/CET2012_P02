package Tools;

import java.io.BufferedWriter;
import java.io.IOException;
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
    public static ArrayList<String> dataStorage = new ArrayList<>();
    static String strForUndoDelete;
    static String strForUndoUpdate;
    /**
     * Variable for dataStorage
     */


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
    public void update(int intUpdateIndex, String strUpdateData) {
        String original = dataStorage.get(intUpdateIndex); //get original data

        // Split and modify
        String[] arrOrigin = original.split("\\s+", 3); //format [FirstName, LastName, email]
        String[] arrUpdate = strUpdateData.split("\\s+", 3); //same as origin regardless param
        String updatedInput;

        for (int i = 0; i < arrUpdate.length; i++) {
            arrOrigin[i] = arrUpdate[i];
        }
        updatedInput = arrOrigin[0] + " " + arrOrigin[1] + " " + arrOrigin[2]; //construct updated data
        strForUndoUpdate = original;

        dataStorage.set(intUpdateIndex, updatedInput);  // update in ArrayList

        //System.out.println("Update");
        //System.out.println("Update " + strUpdateData); //for testing
    }

    public String get(int index){
        return dataStorage.get(index);

    }




    /**
     * Delete function for Delete Command
     *
     * @param intDeleteIndex Index to be updated
     */
    public void delete(int intDeleteIndex) {
        strForUndoDelete = dataStorage.get(intDeleteIndex);
        dataStorage.remove(intDeleteIndex);
        //System.out.println("Delete");
        //System.out.println("Delete " + intDeleteIndex); //for testing
    }




    /**
     * List function for List Command
     *
     */
    public void list() {
        System.out.println("List");
        for (String index : dataStorage) {
            System.out.println(index);
        }
    }

    /**
     * Store data to file
     */
    public void storeToFile() {
        Path filepath = Paths.get("./src/dataStore.txt");

        try (BufferedWriter buff_writer = Files.newBufferedWriter(filepath)) {
            for (String strData : this.dataStorage) {
                String data = strData+ "\n";
                buff_writer.write(data);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

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




