package my.project.log.analysis.service.filters;

import my.project.log.analysis.model.LogMessage;

/**
 * @author Nikolay Horushko
 */
public interface LogFilter {
    void doFilter(LogMessage logMessage);
}
