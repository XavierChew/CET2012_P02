import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.List;

/**
 * Receiver class:
 */
public class Receiver {

    private List<String> dataStorage = new ArrayList<>();


    public void add(String strInput) {
        if (!Objects.equals(strInput, "q")) {
            System.out.println("Add " + strInput);
        }
    }

    public void update(String strInput) {
        if (!Objects.equals(strInput, "q")) {
            System.out.println("Update  " + strInput);
        }
    }

        public void delete(String index) {
        if (dataStorage.contains(index)) {
            dataStorage.remove(index);
        }
        else {
            System.out.println("Data not found");
        }
    }
//    public void undo(String strInput) {
//        if (!Objects.equals(strInput, "q")) {
//            System.out.println("Undo");
//        }
//    }
    public void list() {
            for(String index : dataStorage) {
                System.out.println(index);
            }

        }
//    }
    }
