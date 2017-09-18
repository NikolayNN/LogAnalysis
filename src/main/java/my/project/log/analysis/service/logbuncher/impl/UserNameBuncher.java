package my.project.log.analysis.service.logbuncher.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.logbuncher.LogBuncher;

/**
 * @author Nikolay Horushko
 */
public class UserNameBuncher extends LogBuncher {

    public void addToGroup(LogMessage logMessage) {
        incrementCounter(logMessage.getUserName());
    }
}
