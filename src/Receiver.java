import java.util.Objects;

public class Receiver {
    public void add(String strInput) {
        if (!Objects.equals(strInput, "q")) {
            System.out.println("Add " + strInput);
        }
    }
//    public void update(String strInput) {
//        if (!Objects.equals(strInput, "q")) {
//            System.out.println("Update  " + strInput);
//        }
//    }
//    public void delete(String strInput) {
//        if (!Objects.equals(strInput, "q")) {
//            System.out.println("Delete  " + strInput);
//        }
//    }
//    public void undo(String strInput) {
//        if (!Objects.equals(strInput, "q")) {
//            System.out.println("Undo");
//        }
//    }
//    public void list(String strInput) {
//        if (!Objects.equals(strInput, "list")) {
//            System.out.println("List");
//        }
//    }
}
