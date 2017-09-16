package my.project.log.analysis.service.pathreader;

import java.nio.file.Path;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public interface PathReader {

     Set<Path> getFilePathesListInDirectory(String path);
}
