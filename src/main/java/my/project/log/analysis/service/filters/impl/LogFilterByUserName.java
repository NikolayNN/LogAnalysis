package my.project.log.analysis.service.filters.impl;

import lombok.AllArgsConstructor;
import my.project.log.analysis.exception.LogFilterInterruptingException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.LogFilter;

import java.util.Set;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
public class LogFilterByUserName implements LogFilter {

    private Set<String> userNames;

    @Override
    public void doFilter(LogMessage logMessage) {
        if (!userNames.contains(logMessage.getUserName())) {
            throw new LogFilterInterruptingException();
        }
    }
}
