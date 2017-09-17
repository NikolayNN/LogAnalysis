package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogBuncher;

import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class HourLogBuncher extends LogBuncher {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

    public void addToStatistic(LogMessage logMessage) {
        incrementCounter(logMessage.getDate().format(FORMATTER));
    }
}
