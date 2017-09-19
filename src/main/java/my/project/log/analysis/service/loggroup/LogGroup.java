package my.project.log.analysis.service.loggroup;

import my.project.log.analysis.model.LogMessage;

/**
 * @author Nikolay Horushko
 */
public interface LogGroup {

    String KEY_SEPARATOR = "|";

    String getLogGroupParameter(LogMessage logMessage);
}
