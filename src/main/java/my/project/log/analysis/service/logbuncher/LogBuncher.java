package my.project.log.analysis.service.logbuncher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.project.log.analysis.model.LogMessage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class LogBuncher {

    private Integer FIRST_ELEMENT = 1;

    protected ConcurrentMap<String, Integer> resultMap = new ConcurrentHashMap<>();

    public abstract void addToGroup(LogMessage logMessage);

    protected void incrementCounter(String groupParameter) {
        if (resultMap.containsKey(groupParameter)) {
            Integer value = resultMap.get(groupParameter);
            resultMap.put(groupParameter, ++value);
        } else {
            resultMap.put(groupParameter, FIRST_ELEMENT);
        }
    }
}
