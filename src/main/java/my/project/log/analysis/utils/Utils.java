package my.project.log.analysis.utils;

import my.project.log.analysis.Main;

import java.io.IOException;
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
}
