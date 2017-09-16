package my.project.log.analysis.service.filters.impl.impl;

import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.LogFilter;

import java.util.List;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByCustomMessage implements LogFilter {

    private List<String> patterns;

    public LogFilterByCustomMessage(List<String> patterns) {
        this.patterns = patterns;
    }

    @Override
    public void doFilter(LogMessage logMessage) {
        //todo impl
    }
}
