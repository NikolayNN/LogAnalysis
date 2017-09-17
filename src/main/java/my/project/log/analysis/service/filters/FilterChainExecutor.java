package my.project.log.analysis.service.filters;

import my.project.log.analysis.exception.LogFilterInterruptingException;
import my.project.log.analysis.model.LogMessage;

import java.util.List;

/**
 * @author Nikolay Horushko
 */
public class FilterChainExecutor {

    List<LogFilter> logFilterChain;

    public FilterChainExecutor(List<LogFilter> filterChain) {
        this.logFilterChain = filterChain;
    }

    public boolean isFiltered(LogMessage logMessage) {

        for (LogFilter logFilter : logFilterChain) {
            try {
                logFilter.doFilter(logMessage);
            } catch (LogFilterInterruptingException e) {
                return false;
            }
        }
        return true;
    }
}
