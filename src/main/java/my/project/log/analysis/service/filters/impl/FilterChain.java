package my.project.log.analysis.service.filters.impl;

import my.project.log.analysis.exception.LogFilterInteruptingException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.impl.LogFilterByCustomMessage;
import my.project.log.analysis.service.filters.impl.impl.LogFilterByTime;
import my.project.log.analysis.service.filters.impl.impl.LogFilterByUserName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class FilterChain {

    List<LogFilter> logFilters;

    public FilterChain() {
        this.logFilters = new ArrayList<>();
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

    //todo links instead list
    public void createFilterChain(Set<String> userNames,
                                          Set<String> customMessagePatterns,
                                          LocalDateTime startDate,
                                          LocalDateTime finishDate){

        List resultFilterChain = new ArrayList();

        if(startDate != LocalDateTime.MIN || finishDate != LocalDateTime.MAX){
            resultFilterChain.add(new LogFilterByTime(startDate, finishDate));
        }
        if(userNames.size() != 0) {
            resultFilterChain.add(new LogFilterByUserName(userNames));
        }
        if(customMessagePatterns.size() != 0){
            resultFilterChain.add(new LogFilterByCustomMessage(customMessagePatterns));
        }
    }
}
