package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final String FILE_PATH = "src/main/resources/config.properties";
    private static boolean isLoaded = false;

    private static void loadData() {
        if (!isLoaded) {
            try (FileInputStream input = new FileInputStream(FILE_PATH)) {
                properties.load(input);
                isLoaded = true;
            } catch (Exception e) {
                throw new RuntimeException("Config file not found at given path: " + FILE_PATH, e);
            }
        }
    }

    public static String get(String key) {

        String jvmValue = System.getProperty(key);
        if (jvmValue != null) {
            return jvmValue;
        }

        String envValue = System.getenv(key);
        if (envValue != null) {
            return envValue;
        }
        if (!isLoaded) {
            loadData();
        }

        return properties.getProperty(key);
    }
}
