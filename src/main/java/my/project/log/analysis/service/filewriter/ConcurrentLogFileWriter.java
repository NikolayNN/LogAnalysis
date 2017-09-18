package my.project.log.analysis.service.filewriter;

import my.project.log.analysis.exception.LogAnalysisException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Nikolay Horushko
 */
public class ConcurrentLogFileWriter implements LogFileWriter {
    private String pathToFile;
    private ConcurrentLinkedQueue<String> logMessagesQueue;
    private BufferedWriter bufferedWriter;
    private FileWriterThread backgroundWriter;

    public ConcurrentLogFileWriter(String pathToFile) {
        this.pathToFile = pathToFile;
        logMessagesQueue = new ConcurrentLinkedQueue<>();
        runBackgroundWritingToFile();
    }

    @Override
    public void writeToFile(String logMessage) {
        logMessagesQueue.add(logMessage);
    }

    @Override
    public void setPathToOutputFile(String pathToOutputFile) {
        pathToFile = pathToOutputFile;
    }

    private void runBackgroundWritingToFile() {
        createFile();
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathToFile));
        } catch (IOException e) {
            throw new LogAnalysisException(e);
        }
        backgroundWriter = new FileWriterThread(bufferedWriter, logMessagesQueue);
        new Thread(backgroundWriter, "backgroundWriter").start();
    }

    public void clearResultFile() {
        try {
            backgroundWriter.disable();
            Thread.sleep(200);
            bufferedWriter.close();
            runBackgroundWritingToFile();
        } catch (InterruptedException e) {
            throw new LogAnalysisException(e);
        } catch (IOException e) {
            throw new LogAnalysisException(e);
        }
    }

    private void createFile() {
        Path path = Paths.get(pathToFile);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new LogAnalysisException(e);
        }
    }
}
