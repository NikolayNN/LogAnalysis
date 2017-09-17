package my.project.log.analysis.exception;

/**
 * @author Nikolay Horushko
 */
public class WrongCommandFormatException extends RuntimeException{
    public WrongCommandFormatException(String message) {
        super(message);
    }

    public WrongCommandFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
