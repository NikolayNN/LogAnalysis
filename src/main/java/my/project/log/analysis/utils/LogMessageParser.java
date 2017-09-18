package my.project.log.analysis.utils;

import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.model.LogMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class LogMessageParser {

    private static final String LOG_MESSAGE_EXAMPLE = "2017-09-03 03:16:06 | UserName | Custom Message";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Utils.getProperty("log.date.format"));
    private static final String LOG_MESSAGE_SEPARATOR = "\\|";

    public static LogMessage parce(String sourceMessage) {

        String[] messageTokens = sourceMessage.split(LOG_MESSAGE_SEPARATOR);
        if (messageTokens.length != LOG_MESSAGE_EXAMPLE.split(LOG_MESSAGE_SEPARATOR).length) {
            throw new LogAnalysisException(String.format("The log message '%s' has not valid format", sourceMessage));
        }

        String dateInString = messageTokens[0].trim();
        String userName = messageTokens[1].trim();
        String customMessage = messageTokens[2].trim();

        return new LogMessage(stringToDate(dateInString), userName, customMessage, sourceMessage);
    }

    private static LocalDateTime stringToDate(String dateInString) {
        return LocalDateTime.parse(dateInString, DATE_FORMATTER);
    }
}
