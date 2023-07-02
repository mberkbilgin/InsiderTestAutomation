package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;

    static {
        try {
            // Get the path of the configuration.properties file in the current working directory
            String path = System.getProperty("user.dir") + "/configuration.properties";

            // Create a FileInputStream to read the properties file
            FileInputStream input = new FileInputStream(path);

            // Create a Properties object and load the properties from the input stream
            properties = new Properties();
            properties.load(input);

            // Close the input stream
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }


    /**
     * Get the value of a property based on the given key name.
     *
     * @param keyName the key name of the property
     * @return the value of the property
     */
    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }

}
