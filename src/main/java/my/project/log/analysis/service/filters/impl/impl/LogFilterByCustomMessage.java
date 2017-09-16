package my.project.log.analysis.service.filters.impl.impl;

import my.project.log.analysis.exception.LogFilterInteruptingException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.LogFilter;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByCustomMessage implements LogFilter {

    private Set<Pattern> patterns;

    public LogFilterByCustomMessage(Set<String> stringPatterns) {
        for (String stringPattern : stringPatterns) {
            Pattern pattern = Pattern.compile(stringPattern);
            patterns.add(pattern);
        }
    }

    @Override
    public void doFilter(LogMessage logMessage) {
        String customMessage = logMessage.getCustomMessage();
        for (Pattern pattern : patterns) {
            if(pattern.matcher(customMessage).matches()){
                return;
            }
        }
        throw new LogFilterInteruptingException();
    }
}
