import Commands.*;
import Tools.Invoker;
import Tools.Receiver;

import java.util.Stack;

/**
 * Create and configure the command objects
*/
public class Main {

    public static void main(String[] args) {
        testCase();
        testCase();

    }

    public static void testCase(){
        Stack<Command> history = new Stack<Command>();
        Receiver receiver = new Receiver();
        Command[] cmdlist = {new AddCommand(receiver, "^na123me1 name2 email@email.com"),    // valid
                new AddCommand(receiver, "324name345553 name4 email09_"),           // valid
                new AddCommand(receiver, "n22444ame1 na44me2222 email@email.com ."),  // invalid
                new AddCommand(receiver, "n222ame1 nr34r3ame2 .email @email.com"),   // invalid
                new AddCommand(receiver, "n43tame1 22n4r43ame2 -email@email.com"),   // invalid
                new AddCommand(receiver, "nam22e1 name3222re2222  _email@email.com"),   // valid
                new AddCommand(receiver, "name1 nr34r11134ame2222 222email.@email.com"),   // invalid
                new AddCommand(receiver, "name1 n111ame2 eggg2 e347899mail-@email.com"),   // invalid
                new AddCommand(receiver, "na34r34me1 nar34rme2 email --@email.com"),  // invalid
                new AddCommand(receiver, "n222ame1 name2 email_@emai l.com"),   // valid
                new AddCommand(receiver, "name1 name2 email__f fs@email.com"),  // valid
                new AddCommand(receiver, "name1 name2 e..mail@email.com"),  // invalid
                new AddCommand(receiver, "name1 name2 e--mail@ema il.com"),  // invalid
                new AddCommand(receiver, "sfdname1 dfname2 e__mail@email.com"),  // valid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL.com"),    // valid
                new AddCommand(receiver, "namfdsfe1 name2 EMAIL@EMAIL.COM"),    // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@.EMAIL.com"),   // invalid
                new AddCommand(receiver, "name1 namfdse2 EMAIL@-EMAIL.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAILsfd..com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@EMAIL-.com"),   // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E.MAIL.com"),   // valid
                new AddCommand(receiver, "name1 name2 EMAIL@E-MAIL.com"),   // valid
                new AddCommand(receiver, "name1 name2 EMAIL@E..MAIL.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.com"),  // invalid
                new AddCommand(receiver, "name1 name2 EMAIL@E--MAIL.co."),  // invalid
                new ListCommand(receiver),
                new UpdateCommand(receiver, "1 name lastname .abc@domain.com"),          // starts with dot
                new UpdateCommand(receiver, "1 name lastname abc.@domain.com"),          // ends with dot
                new UpdateCommand(receiver, "1 name lastname -abc@domain.com"),          // starts with dash
                new UpdateCommand(receiver, "1 name lastname abc-@domain.com"),          // ends with dash
                new UpdateCommand(receiver, "1 name lastname abc..def@domain.com"),      // consecutive dots
                new UpdateCommand(receiver, "1 name lastname abc--def@domain.com"),      // consecutive dashes
                new UpdateCommand(receiver, "1 name lastname abc.-def@domain.com"),      // mixed consecutive dot/dash
                new UpdateCommand(receiver, "1 name lastname abc@domain.com"),           // control test
                new UpdateCommand(receiver, "1 name lastname user@-abc.com"),            // starts with dash
                new UpdateCommand(receiver, "1 name lastname user@abc-.com"),            // ends with dash
                new UpdateCommand(receiver, "1 name lastname user@.abc.com"),            // starts with dot
                new UpdateCommand(receiver, "1 name lastname user@abc..com"),            // consecutive dots
                new UpdateCommand(receiver, "1 name lastname user@abc--def.com"),        // consecutive dashes
                new UpdateCommand(receiver, "1 name lastname user@abc.c"),               // domain suffix too short
                new UpdateCommand(receiver, "1 name lastname user@abc.comm"),            // domain suffix too long
                new UpdateCommand(receiver, "1 name lastname user@abc.CoM"),             // uppercase letters in domain suffix
                new UpdateCommand(receiver, "1 name lastname user@abc.123"),             // digits not allowed in suffix
                new UpdateCommand(receiver, "1 name lastname user@abc.c_m"),             // underscore not allowed in suffix
                new UpdateCommand(receiver, "1 name lastname user@abc.def-"),
                new ListCommand(receiver),

                /* TEST FOR DELETE COMMAND */
// ✅ VALID TEST CASES
                new DeleteCommand(receiver, " 1 "),        // ✅ Valid: typical index
                new DeleteCommand(receiver, "5"),       // ✅ Valid: larger index

// ❌ INVALID TEST CASES
                new DeleteCommand(receiver, "999"),      // ❌ Index out of bounds
                new DeleteCommand(receiver, ""),         // ❌ Empty payload
                new DeleteCommand(receiver, "1, 2"),     // ❌ Wrong payload
                new DeleteCommand(receiver, "0"),        // ❌ Non-numeric input
                new DeleteCommand(receiver, "1abc"),     // ❌ Alphanumeric string
                new DeleteCommand(receiver, "-1"),       // ❌ Negative index
                new DeleteCommand(receiver, "0"),        // ❌ Zero index (if 1-based indexing is assumed)
                new DeleteCommand(receiver, " "),        // ❌ Space as input
                new DeleteCommand(receiver, "1 2"),      // ❌ Extra unexpected arguments
                new DeleteCommand(receiver, "3.14"),     // ❌ Decimal number
                new DeleteCommand(receiver, "#1"),       // ❌ Symbol-prefixed input
                new ListCommand(receiver),

                /* TEST FOR UNDO COMMAND */
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new UndoCommand(receiver, history),
                new ListCommand(receiver),};
//        Command[] cmdlist = {
////                new AddCommand(receiver,"first_name Last_name Email"),
//                new AddCommand(receiver,"fist second third"),
//                new UpdateCommand(receiver, "1 aa"),
//                new ListCommand(receiver),
//        };

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(cmdlist);
        invoker.executeCommand(history);

    }

//    public static void main(String[] args) {
//
//        String strAddCommand1 = "firstName1 lastName1 email1";
//        String strAddCommand2 = "secondName 2ndlast email2";
//        String strAddCommand3 = "3rdName 3rdlast email3";
//        String strUpdateCommand = "1 FirstNameChange1";
//        String strUpdateCommand2 = "2 FirstNameChange2 lastname2";
//        String strDeleteCommand = "1"; //for delete
//
//        Stack<Command> history = new Stack<>(); //history stack
//
//        // Create receiver
//        Receiver receiver = new Receiver();
//
//        //might need to review this to make it more automatic start
//        // Create command objects
//        Command addCommand = new AddCommand(receiver, strAddCommand1);
//        Command addCommand1 = new AddCommand(receiver, strAddCommand2);
//        Command addCommand2 = new AddCommand(receiver, strAddCommand3);
//        Command updateCommand = new UpdateCommand(receiver, strUpdateCommand);
//        Command updateCommand2 = new UpdateCommand(receiver, strUpdateCommand2);
//        Command deleteCommand = new DeleteCommand(receiver, strDeleteCommand);
//        Command deleteCommand2 = new DeleteCommand(receiver, strDeleteCommand);
//        Command undoCommand = new UndoCommand(receiver, history);
//        Command listCommand = new ListCommand(receiver);
//
//        // Store to command objects array
////        Command[] cmdToExecute = {addCommand, updateCommand, listCommand};
//        Command[] cmdToExecute = {addCommand1, addCommand2, updateCommand, deleteCommand, undoCommand, listCommand};
//        Invoker invoker = new Invoker();
//
//        // Set and execute commands
//        invoker.setCommandsForExecution(cmdToExecute);
//        invoker.executeCommand(history);
//
//        // Store to file
//        receiver.storeToFile();
//    }
}