package my.project.log.analysis.service.message.parcer;

import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nikolay Horushko
 */
public class LogMessageParcer {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(Utils.getProperty("log.date.format"));

    //2017-09-16 12:11:06 | UserName1 | CustomMessage
    public LogMessage parce(String message){

        String[] messageTokens = message.split("\\|");
        if(messageTokens.length != 3){
            throw new LogAnalysisException(String.format("The log message '%s' has not valid format", message));
        }

        String dateInString = messageTokens[0].trim();
        String userName = messageTokens[1].trim();
        String customMessage = messageTokens[2].trim();

        return new LogMessage(stringToDate(dateInString), userName, customMessage);
    }

    private Date stringToDate (String dateInString){

        Date date;
        try {
            date = dateFormat.parse(dateInString);
        } catch (ParseException e) {
            throw new LogAnalysisException(String.format("The date '%s' has not valid format", dateInString), e);
        }
        return date;
    }
}
