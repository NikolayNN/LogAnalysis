package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogGroup;

/**
 * @author Nikolay Horushko
 */
public class UserNameGroup extends LogGroupDecorator {

    public UserNameGroup(LogGroup logGroup) {
        super(logGroup);
    }

    @Override
    public String getLogGroupParameter(LogMessage logMessage) {
        return String.format("%s%s%s", super.getLogGroupParameter(logMessage), KEY_SEPARATOR, logMessage.getUserName());
    }
}
