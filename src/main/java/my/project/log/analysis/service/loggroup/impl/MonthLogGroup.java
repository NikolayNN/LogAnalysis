package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogGroup;
import my.project.log.analysis.service.loggroup.LogGroupDecorator;

import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class MonthLogGroup extends LogGroupDecorator {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public MonthLogGroup(LogGroup logGroup) {
        super(logGroup);
    }

    @Override
    public String getLogGroupParameter(LogMessage logMessage) {
        return super.getLogGroupParameter(logMessage) + KEY_SEPARATOR + logMessage.getDate().format(FORMATTER);
    }
}
