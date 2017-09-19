package my.project.log.analysis.utils;

import my.project.log.analysis.Main;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Nikolay Horushko
 */
public class Utils {

    private static final String propertyFile = "application.properties";

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        ClassLoader loader = Main.class.getClassLoader();
        try {
            properties.load(loader.getResourceAsStream(propertyFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }

    public static void printMap(Map<String, Integer> data){
        if(data.size() == 0){
            System.out.println("We can't find log messages with your filter");
        }

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            System.out.println((String.format("%s - %s %s", entry.getKey(), entry.getValue(),
                    System.getProperty("line.separator"))));
        }
    }
}
