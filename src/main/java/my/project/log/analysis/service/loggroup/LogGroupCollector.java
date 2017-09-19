package my.project.log.analysis.service.loggroup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Nikolay Horushko
 */
public class LogGroupCollector {
    private Integer FIRST_ELEMENT = 1;

    private ConcurrentMap<String, Integer> resultMap;

    public LogGroupCollector() {
        resultMap = new ConcurrentHashMap<>();
    }

    public synchronized void addToGroup(String groupParameter) {
        if (resultMap.containsKey(groupParameter)) {
            Integer value = resultMap.get(groupParameter);
            resultMap.put(groupParameter, ++value);
        } else {
            resultMap.put(groupParameter, FIRST_ELEMENT);
        }
    }

    public void clearResultMap(){
        resultMap = new ConcurrentHashMap<>();
    }

    public ConcurrentMap<String, Integer> getResultMap() {
        return resultMap;
    }
}
