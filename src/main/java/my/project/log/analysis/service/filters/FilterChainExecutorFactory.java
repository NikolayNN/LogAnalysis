package my.project.log.analysis.service.filters;

import my.project.log.analysis.service.filters.impl.LogFilterByCustomMessage;
import my.project.log.analysis.service.filters.impl.LogFilterByTime;
import my.project.log.analysis.service.filters.impl.LogFilterByUserName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class FilterChainExecutorFactory {

    public FilterChainExecutor createFilterChain(Set<String> userNames,
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
        return new FilterChainExecutor(resultFilterChain);
    }
}
