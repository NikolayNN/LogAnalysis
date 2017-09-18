package my.project.log.analysis.service.logbuncher.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.logbuncher.LogBuncher;

import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class DayLogBuncher extends LogBuncher {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void addToGroup(LogMessage logMessage) {
        incrementCounter(logMessage.getDate().format(FORMATTER));
    }
}
