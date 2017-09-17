package my.project.log.analysis.exception;

/**
 * @author Nikolay Horushko
 */
public class LogAnalysisException extends RuntimeException {

    public LogAnalysisException(String message) {
        super(message);
    }

    public LogAnalysisException(Throwable cause) {
        super(cause);
    }
}
