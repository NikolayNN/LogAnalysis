package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogBuncher;

/**
 * @author Nikolay Horushko
 */
public class UserNameBuncher extends LogBuncher {

    public void addToStatistic(LogMessage logMessage) {
        incrementCounter(logMessage.getUserName());
    }
}
