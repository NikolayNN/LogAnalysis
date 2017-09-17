package my.project.log.analysis.service.logcounter;

import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.model.GroupBy;
import my.project.log.analysis.service.logcounter.impl.*;

/**
 * @author Nikolay Horushko
 */
public class LogGroupFactory {

    public LogGroup createLogGroup(GroupBy groupBy) {
        if (groupBy == GroupBy.HOUR) {
            return new HourLogGroup();
        }
        if (groupBy == GroupBy.DAY) {
            return new DayLogGroup();
        }
        if (groupBy == GroupBy.MONTH) {
            return new MonthLogGroup();
        }
        if (groupBy == GroupBy.YEAR) {
            return new YearLogGroup();
        }
        if (groupBy == GroupBy.USERNAME) {
            return new UserNameGroup();
        }
        throw new LogAnalysisException(String.format("Unexpected group Parameter '%s'", groupBy));
    }
}
