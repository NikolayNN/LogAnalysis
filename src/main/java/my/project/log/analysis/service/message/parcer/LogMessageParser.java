package my.project.log.analysis.service.message.parcer;

import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class LogMessageParser {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Utils.getProperty("log.date.format"));

    public LogMessage parce(String sourceMessage) {

        String[] messageTokens = sourceMessage.split("\\|");
        if (messageTokens.length != 3) {
            throw new LogAnalysisException(String.format("The log message '%s' has not valid format", sourceMessage));
        }

        String dateInString = messageTokens[0].trim();
        String userName = messageTokens[1].trim();
        String customMessage = messageTokens[2].trim();

        return new LogMessage(stringToDate(dateInString), userName, customMessage, sourceMessage);
    }

    private LocalDateTime stringToDate(String dateInString) {
        return LocalDateTime.parse(dateInString, dateFormatter);
    }
}
