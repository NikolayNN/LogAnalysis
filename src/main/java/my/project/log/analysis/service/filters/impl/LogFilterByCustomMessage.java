package my.project.log.analysis.service.filters.impl;

import my.project.log.analysis.exception.LogFilterInteruptingException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.LogFilter;

import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByCustomMessage implements LogFilter {

    private Set<String> patterns;

    public LogFilterByCustomMessage(Set<String> patterns) {
        this.patterns = patterns;
    }

    @Override
    public void doFilter(LogMessage logMessage) {
        String customMessage = logMessage.getCustomMessage();
        for (String pattern : patterns) {
            if(customMessage.contains(pattern)){
                return;
            }
        }
        throw new LogFilterInteruptingException();
    }
}
