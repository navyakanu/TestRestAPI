package utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesLoader {

    public static Properties prop;

    public static void LoadProperties() throws IOException {

        prop = new Properties();
        InputStream input = null;
        String pathtoPropertiesFile = System.getProperty("user.dir") + "/src/main/resources/config.properties";
        System.out.println(pathtoPropertiesFile);
        input = new FileInputStream(pathtoPropertiesFile);
        prop.load(input);

    }

    public static String getPropertyDetails(String s) {

        String details = PropertiesLoader.prop.getProperty(s);
        return details;
    }


}