package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    public static String GetConfigReader(String parameter) throws IOException {
        FileInputStream file = new FileInputStream("./config/ApplicationConfig.properties");
        Properties properties = new Properties();
        properties.load(file);
        return properties.getProperty(parameter);
    }
}
