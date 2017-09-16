package my.project.log.analysis.service.logcounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.project.log.analysis.model.LogMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class LogGroup {

    private final Integer FIRST_ELEMENT = 1;

    protected Map<String, Integer> resultMap;

    public abstract void addToStatistic(LogMessage logMessage);

    protected void incrementCounter(String groupParameter) {
        if (resultMap.containsKey(groupParameter)) {
            Integer value = resultMap.get(groupParameter);
            resultMap.put(groupParameter, value++);
        } else {
            resultMap.put(groupParameter, FIRST_ELEMENT);
        }
    }
}
