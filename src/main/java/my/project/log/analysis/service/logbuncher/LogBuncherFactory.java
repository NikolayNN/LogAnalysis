package my.project.log.analysis.service.logbuncher;

import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.model.GroupBy;
import my.project.log.analysis.service.logbuncher.impl.*;

/**
 * @author Nikolay Horushko
 */
public class LogBuncherFactory {

    public LogBuncher createLogGroup(GroupBy groupBy) {
        if (groupBy == GroupBy.HOUR) {
            return new HourLogBuncher();
        }
        if (groupBy == GroupBy.DAY) {
            return new DayLogBuncher();
        }
        if (groupBy == GroupBy.MONTH) {
            return new MonthLogBuncher();
        }
        if (groupBy == GroupBy.YEAR) {
            return new YearLogBuncher();
        }
        if (groupBy == GroupBy.USERNAME) {
            return new UserNameBuncher();
        }
        throw new LogAnalysisException(String.format("Unexpected group Parameter '%s'", groupBy));
    }
}
