package my.project.log.analysis.service.filters.impl;


import lombok.AllArgsConstructor;
import my.project.log.analysis.exception.LogFilterInterruptingException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.LogFilter;

import java.time.LocalDateTime;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
public class LogFilterByTime implements LogFilter {

    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    @Override
    public void doFilter(LogMessage logMessage) {
        LocalDateTime date = logMessage.getDate();

        if(date.isBefore(startDate) || date.isAfter(finishDate)){
            throw new LogFilterInterruptingException();
        }
    }
}
