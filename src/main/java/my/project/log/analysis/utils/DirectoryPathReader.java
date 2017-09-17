package my.project.log.analysis.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Nikolay Horushko
 */
public class DirectoryPathReader {

    public static List<Path> getFilePathesListInDirectory(String path) {
        List<Path> result = new LinkedList<>();
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
