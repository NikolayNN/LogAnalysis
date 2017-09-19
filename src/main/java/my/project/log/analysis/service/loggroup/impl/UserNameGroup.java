package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogGroup;
import my.project.log.analysis.service.loggroup.LogGroupDecorator;

/**
 * @author Nikolay Horushko
 */
public class UserNameGroup extends LogGroupDecorator {

    public UserNameGroup(LogGroup logGroup) {
        super(logGroup);
    }

    @Override
    public String getLogGroupParameter(LogMessage logMessage) {
        return super.getLogGroupParameter(logMessage) + KEY_SEPARATOR + logMessage.getUserName();
    }
}
