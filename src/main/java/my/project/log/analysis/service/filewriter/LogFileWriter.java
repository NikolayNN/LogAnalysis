package my.project.log.analysis.service.filewriter;

/**
 * @author Nikolay Horushko
 */
public interface LogFileWriter {

    void writeToFile(String logMessage);

    void setPathToOutputFile(String pathToOutputFile);

    void clearResultFile();
}
