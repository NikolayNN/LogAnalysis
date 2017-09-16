package my.project.log.analysis.service.filters.impl.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.LogFilter;

import java.util.List;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByUserName implements LogFilter {

    private List<String> usernames;

    public LogFilterByUserName(List<String> usernames) {
        this.usernames = usernames;
    }

    @Override
    public void doFilter(LogMessage logMessage) {
        //todo impl
    }
}
