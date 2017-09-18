package my.project.log.analysis.service.filewriter;

import my.project.log.analysis.exception.LogAnalysisException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Nikolay Horushko
 */
public class FileWriterThread implements Runnable {

    private BufferedWriter bufferedWriter;
    private ConcurrentLinkedQueue<String> logMessagesQueue;
    private boolean isActive;

    public FileWriterThread(BufferedWriter bufferedWriter, ConcurrentLinkedQueue<String> logMessagesQueue) {
        this.bufferedWriter = bufferedWriter;
        this.logMessagesQueue = logMessagesQueue;
        this.isActive = true;
    }

    public void disable() {
        isActive = false;
    }

    @Override
    public void run() {
        synchronized (logMessagesQueue) {
            while (isActive) {
                if (!logMessagesQueue.isEmpty()) {
                    try {
                        bufferedWriter.write(logMessagesQueue.poll() + System.lineSeparator());
                        bufferedWriter.flush();
                    } catch (IOException ex) {
                        throw new LogAnalysisException(ex);
                    }
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
