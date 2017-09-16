package my.project.log.analysis.service.logcounter.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.logcounter.LogGroup;

import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class YearLogGroup extends LogGroup{
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    public void addToStatistic(LogMessage logMessage) {
        incrementCounter(logMessage.getDate().format(FORMATTER));
    }
}
