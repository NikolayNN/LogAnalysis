package my.project.log.analysis.service;

import my.project.log.analysis.exception.LogAnalysisException;
import my.project.log.analysis.service.logcounter.LogCounter;
import my.project.log.analysis.service.pathreader.PathReader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Nikolay Horushko
 */
public class LogAnalysis {

    LogCounter logCounter;
    PathReader pathReader;
    FileWriter fileWriter;

    public LogAnalysis(LogCounter logCounter, PathReader pathReader, FileWriter fileWriter) {
        this.logCounter = logCounter;
        this.pathReader = pathReader;
        this.fileWriter = fileWriter;
    }

//    public void runAnalys (String path){
//        try (Stream<String> stream = Files.lines(Paths.get("res/nashorn1.js"))) {
//            stream
//                    .filter(line -> contains(line, t))
//                    .map(line -> new Obj(line))
//                    .forEachOrdered(System.out::println);
//        }catch (IOException e){
//            throw new LogAnalysisException(e);
//        }
//
//    }

//    public static void main(String[] args) {
//        String[] t = new String[]{"qs", "we"};
//
//    }
//
    private static boolean contains (String s, String[] token){
        for (String s1 : token) {
            if(!s.contains(s1)){
                return false;
            }
        }
        return true;
    }

    static class Obj{

        String line;

        public Obj(String line) {
            this.line = line + 1;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "line='" + line + '\'' +
                    '}';
        }
    }
}
