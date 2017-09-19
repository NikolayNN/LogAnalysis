package my.project.log.analysis.service.loggroup.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.loggroup.LogGroup;

/**
 * @author Nikolay Horushko
 */
public class BasicLogGroup implements LogGroup {

    @Override
    public String getLogGroupParameter(LogMessage logMessage) {
        return "";
    }
}
