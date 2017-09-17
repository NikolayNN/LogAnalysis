package my.project.log.analysis.service.filewriter;

import java.util.Map;

/**
 * @author Nikolay Horushko
 */
public interface LogFileWriter {

    void writeToFile(Map<String, Integer> data);
    void setPathToOutputFile(String pathToOutputFile);
}
