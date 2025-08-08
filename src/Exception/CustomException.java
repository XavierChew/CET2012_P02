package Exception;

/**
 * CustomException is a user-defined unchecked exception that extends {@link RuntimeException}.
 * It is used to indicate application-specific runtime errors with custom messages.
 */
public class CustomException extends RuntimeException {

    /**
     * Constructs a new CustomException with the specified detail message.
     *
     * @param msg the detail message explaining the reason for the exception
     */
    public CustomException(String msg) {
        super(msg);
    }
}
