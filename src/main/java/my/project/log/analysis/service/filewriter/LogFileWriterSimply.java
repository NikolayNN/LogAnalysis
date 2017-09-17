package my.project.log.analysis.service.filewriter;

import lombok.AllArgsConstructor;
import my.project.log.analysis.exception.LogAnalysisException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
public class LogFileWriterSimply implements LogFileWriter {

    private String pathToFile;

    @Override
    public void writeToFile(Map<String, Integer> data) {
        createFile();
        try (BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(pathToFile)))) {
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                bufferedWriter.write(String.format("%s - %s %s", entry.getKey(), entry.getValue(), System.getProperty("line.separator")));
            }
        } catch (IOException ex) {
            throw new LogAnalysisException(ex);
        }
    }

    @Override
    public void setPathToOutputFile(String pathToOutputFile) {
        pathToFile = pathToOutputFile;
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
