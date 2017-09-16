package my.project.log.analysis.service.filters.impl;

import my.project.log.analysis.model.LogMessage;

/**
 * @author Nikolay Horushko
 */
public interface LogFilter {
    void doFilter(LogMessage logMessage);
}
