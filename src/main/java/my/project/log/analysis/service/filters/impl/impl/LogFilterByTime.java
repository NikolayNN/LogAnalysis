package my.project.log.analysis.service.filters.impl.impl;


import lombok.AllArgsConstructor;
import my.project.log.analysis.exception.LogFilterInteruptingException;
import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.LogFilter;

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
        LocalDateTime currentDate = logMessage.getDate();

        if(currentDate.isBefore(startDate) || currentDate.isAfter(finishDate)){
            throw new LogFilterInteruptingException();
        }
    }
}
