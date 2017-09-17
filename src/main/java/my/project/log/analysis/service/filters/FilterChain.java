package my.project.log.analysis.service.filters;

import my.project.log.analysis.exception.LogFilterInteruptingException;
import my.project.log.analysis.model.LogMessage;

import java.util.List;

/**
 * @author Nikolay Horushko
 */
public class FilterChain {

    List<LogFilter> logFilters;

    public FilterChain(List<LogFilter> filterChain) {
        this.logFilters = filterChain;
    }

    public boolean isFiltered(LogMessage logMessage) {

        for (LogFilter logFilter : logFilters) {
            try {
                logFilter.doFilter(logMessage);
            } catch (LogFilterInteruptingException e) {
                return false;
            }
        }
        return true;
    }
}
