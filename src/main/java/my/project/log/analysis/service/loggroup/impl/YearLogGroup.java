package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogGroup;

import java.time.format.DateTimeFormatter;

/**
 * @author Nikolay Horushko
 */
public class YearLogGroup extends LogGroupDecorator {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    public YearLogGroup(LogGroup logGroup) {
        super(logGroup);
    }

    @Override
    public String getLogGroupParameter(LogMessage logMessage) {
        return String.format("%s%s%s", super.getLogGroupParameter(logMessage), KEY_SEPARATOR,
                logMessage.getDate().format(FORMATTER));
    }
}
