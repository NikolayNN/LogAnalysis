package my.project.log.analysis.service.loggroup;

import my.project.log.analysis.model.LogMessage;

/**
 * @author Nikolay Horushko
 */
public class LogGroupDecorator implements LogGroup {

    protected LogGroup logGroup;

    public LogGroupDecorator(LogGroup logGroup) {
        this.logGroup = logGroup;
    }

    @Override
    public String getLogGroupParameter(LogMessage logMessage) {
        return this.logGroup.getLogGroupParameter(logMessage);
    }
}
