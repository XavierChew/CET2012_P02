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
        dataStorage.add(strInput);
        System.out.println("Add " + strInput);
    }

    public void update(int intUpdateIndex, String UpdateInput) {
        String original = dataStorage.get(intUpdateIndex);

        // Split and modify
        String[] arrOrigin = original.split("\\s+", 3); // split into [First, Last, Email]
        String[] arrUpdate= UpdateInput.split("\\s+", 3); // split into [First, Last, Email]
        String updatedInput = "";

        for(int i = 0; i < arrOrigin.length; i++){
            if (arrUpdate[i] != null) {
                arrOrigin[i] = arrUpdate[i];
            }
        }
        updatedInput = arrOrigin[0] + " " + arrOrigin[1] + " " + arrOrigin[2];

        dataStorage.set(intUpdateIndex, updatedInput);  // update in ArrayList

//        if (dataStorage.contains(intUpdateIndex)) {
//
//        }
//        else {
//            System.out.println("Data not found");
//        }
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
