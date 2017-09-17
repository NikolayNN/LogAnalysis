package my.project.log.analysis.service.filters.impl.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.LogFilter;

/**
 * @author Nikolay Horushko
 */
public class LogFilterDefault implements LogFilter {

    @Override
    public void doFilter(LogMessage logMessage) {
        //do nothing default filter
    }
}
