package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    static Properties properties = new Properties();

    public static String getProperties(String file, String property){

        file = "src/main/resources/".concat(file);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(property);
    }
}
