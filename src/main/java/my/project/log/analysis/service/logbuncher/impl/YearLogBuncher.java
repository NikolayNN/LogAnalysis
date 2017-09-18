package my.project.log.analysis.service.logbuncher.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.logbuncher.LogBuncher;

import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class YearLogBuncher extends LogBuncher {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    public void addToGroup(LogMessage logMessage) {
        incrementCounter(logMessage.getDate().format(FORMATTER));
    }
}
