package my.project.log.analysis.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.service.filewriter.FileWriter;
import my.project.log.analysis.service.filters.impl.FilterChain;
import my.project.log.analysis.service.logcounter.LogGroup;
import my.project.log.analysis.service.message.parcer.LogMessageParcer;
import my.project.log.analysis.utils.PathReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
@Setter
public class LogAnalyser {

    private LogMessageParcer logParcer;
    private FilterChain filterChain;
    private LogGroup logGroup;
    private FileWriter fileWriter;

    public LogAnalyser(LogMessageParcer logParcer, FileWriter fileWriter) {
        this.logParcer = logParcer;
        this.fileWriter = fileWriter;
    }

    public void runAnalysis(String path) {
        List<Path> paths = PathReader.getFilePathesListInDirectory(path);

        for (Path filePath : paths) {
            try (Stream<String> stream = Files.lines(Paths.get(filePath.toUri()))) {
                stream
                        .map(line -> logParcer.parce(line))
                        .filter(line -> filterChain.isFiltered(line))
                        .forEach(logGroup::addToStatistic);
            } catch (IOException e) {
                throw new LogAnalysisException(e);
            }
        }
        System.out.println(logGroup.getResultMap().toString());
    }

}
