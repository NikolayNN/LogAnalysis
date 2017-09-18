package my.project.log.analysis.service.filewriter;

import lombok.AllArgsConstructor;
import my.project.log.analysis.exception.LogAnalysisException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
public class FileWriterThread implements Runnable{

    private BufferedWriter bufferedWriter;
    private ConcurrentLinkedQueue<String> logMessagesQueue;


    @Override
    public void run() {
        synchronized (logMessagesQueue){
            while (true){
                if(!logMessagesQueue.isEmpty()){
                    try{
                        bufferedWriter.write(logMessagesQueue.poll() + System.lineSeparator());
                        bufferedWriter.flush();
                    } catch (IOException ex){
                        throw new LogAnalysisException(ex);
                    }
                }else{
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
