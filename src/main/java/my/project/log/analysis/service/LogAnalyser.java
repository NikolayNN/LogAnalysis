package my.project.log.analysis.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.service.filewriter.LogFileWriter;
import my.project.log.analysis.service.filters.FilterChainExecutor;
import my.project.log.analysis.service.loggroup.LogBuncher;
import my.project.log.analysis.utils.LogMessageParser;
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

    private FilterChainExecutor logFilterChainExecutor;
    private LogBuncher logBuncher;
    private LogFileWriter logFileWriter;

    public LogAnalyser(LogFileWriter logFileWriter) {
        this.logFileWriter = logFileWriter;
    }

    public void runAnalysis(String pathToDirectoryWithLogs, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (Path logFile : DirectoryPathReader.getFilePathesInDirectory(pathToDirectoryWithLogs)) {
            executor.execute(new Reader(logFile));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        logFileWriter.writeToFile(new TreeMap<>(logBuncher.getResultMap()));
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
                        .map(line -> LogMessageParser.parce(line))
                        .filter(line -> logFilterChainExecutor.isFiltered(line))
                        .forEach(logBuncher::addToStatistic);
            } catch (IOException e) {
                throw new LogAnalysisException(e);
            }
        }
    }


}
