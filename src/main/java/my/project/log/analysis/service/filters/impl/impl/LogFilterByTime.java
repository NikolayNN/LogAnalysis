package my.project.log.analysis.service.filters.impl.impl;


import my.project.log.analysis.model.LogMessage;
import my.project.log.analysis.service.filters.impl.LogFilter;

import java.util.Date;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByTime implements LogFilter {

    private Date startDate;
    private Date finishDate;

    public LogFilterByTime(Date startDate, Date finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }


    @Override
    public void doFilter(LogMessage logMessage) {
        //todo impl
    }
}
