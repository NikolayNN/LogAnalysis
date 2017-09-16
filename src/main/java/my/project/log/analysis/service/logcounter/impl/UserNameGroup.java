package my.project.log.analysis.service.logcounter.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.logcounter.LogGroup;

/**
 * @author Nikolay Horushko
 */
public class UserNameGroup extends LogGroup {

    public void addToStatistic(LogMessage logMessage) {
        incrementCounter(logMessage.getUserName());
    }
}
