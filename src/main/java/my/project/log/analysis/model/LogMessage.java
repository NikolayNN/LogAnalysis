package my.project.log.analysis.model;

import java.util.Date;

/**
 * @author Nikolay Horushko
 */
public class LogMessage {
    private Date date;
    private String userName;
    private String customMessage;

    public LogMessage(Date date, String userName, String customMessage) {
        this.date = date;
        this.userName = userName;
        this.customMessage = customMessage;
    }
}
