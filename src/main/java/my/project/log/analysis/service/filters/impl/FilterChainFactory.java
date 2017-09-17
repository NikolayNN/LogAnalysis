package my.project.log.analysis.service.filters.impl;

import my.project.log.analysis.service.filters.impl.impl.LogFilterByCustomMessage;
import my.project.log.analysis.service.filters.impl.impl.LogFilterByTime;
import my.project.log.analysis.service.filters.impl.impl.LogFilterByUserName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class FilterChainFactory {

    public List<LogFilter> createFilterChain(Set<String> userNames,
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
        return resultFilterChain;
    }
}
