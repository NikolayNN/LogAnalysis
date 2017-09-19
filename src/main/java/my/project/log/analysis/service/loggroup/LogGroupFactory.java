package my.project.log.analysis.service.loggroup;

import my.project.log.analysis.model.GroupBy;
import my.project.log.analysis.service.loggroup.impl.*;

import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class LogGroupFactory {

    public LogGroup createLogGroup(Set<GroupBy> groupBySet) {
        LogGroup result = new BasicLogGroup();

        for (GroupBy groupBy : groupBySet) {
            if (groupBy == GroupBy.HOUR) {
                result = new HourLogGroup(result);
                break;
            }
            if (groupBy == GroupBy.DAY) {
                result = new DayLogGroup(result);
                break;
            }
            if (groupBy == GroupBy.MONTH) {
                result = new MonthLogGroup(result);
                break;
            }
            if (groupBy == GroupBy.YEAR) {
                result = new YearLogGroup(result);
                break;
            }
        }

        for (GroupBy groupBy : groupBySet) {
            if (groupBy == GroupBy.USERNAME) {
                result = new UserNameGroup(result);
                break;
            }
        }
        return result;
    }
}
