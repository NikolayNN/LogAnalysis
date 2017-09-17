package my.project.log.analysis.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.service.filewriter.LogFileWriter;
import my.project.log.analysis.service.filters.FilterChain;
import my.project.log.analysis.service.logcounter.LogGroup;
import my.project.log.analysis.service.message.parcer.LogMessageParser;
import my.project.log.analysis.utils.DirectoryPathReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
@Getter
@Setter
public class LogAnalyser {

    private LogMessageParser logParcer;
    private FilterChain filterChain;
    private LogGroup logGroup;
    private LogFileWriter logFileWriter;

    public LogAnalyser(LogMessageParser logParcer, LogFileWriter logFileWriter) {
        this.logParcer = logParcer;
        this.logFileWriter = logFileWriter;
    }

    public void runAnalysis(String pathToDirectoryWithLogs, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (Path logFile : DirectoryPathReader.getFilePathesListInDirectory(pathToDirectoryWithLogs)) {
            executor.execute(new Reader(logFile));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        logFileWriter.writeToFile(new TreeMap<>(logGroup.getResultMap()));
    }

    private final class Reader implements Runnable {

        private final Path path;

        public Reader(Path path){
            this.path = path;
        }

        @Override
        public void run() {
            try (Stream<String> stream = Files.lines(Paths.get(path.toUri()))) {
                stream
                        .map(line -> logParcer.parce(line))
                        .filter(line -> filterChain.isFiltered(line))
                        .forEach(logGroup::addToStatistic);
            } catch (IOException e) {
                throw new LogAnalysisException(e);
            }
        }
    }


}
