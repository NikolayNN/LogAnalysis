package my.project.log.analysis.service.filters.impl;

import javafx.collections.transformation.FilteredList;
import my.project.log.analysis.model.LogMessage;

import java.util.List;

/**
 * @author Nikolay Horushko
 */
public class FilterManager {

    List<LogFilter> logFilters;

    public FilterManager(List<LogFilter> logFilters) {
        this.logFilters = logFilters;
    }

    public void execute(LogMessage logMessage){
        for (LogFilter logFilter : logFilters) {
            logFilter.doFilter(logMessage);
        }
    }
}
