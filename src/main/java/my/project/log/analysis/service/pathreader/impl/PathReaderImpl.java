package my.project.log.analysis.service.pathreader.impl;

import my.project.log.analysis.service.pathreader.PathReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Nikolay Horushko
 */
public class PathReaderImpl implements PathReader {

    public Set<Path> getFilePathesListInDirectory(String path) {
        Set<Path> result = new HashSet<>();
        try(Stream<Path> stream = Files.walk(Paths.get(path))) {
            stream
                    .filter(Files::isRegularFile)
                    .forEach(result::add);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return result;
    }
}
