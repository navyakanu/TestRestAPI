import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.PropertiesLoader;

import java.io.IOException;


public class FactoryClass {


    public static void Initialise() throws IOException {
        PropertiesLoader.LoadProperties();
        RestAssured.baseURI = PropertiesLoader.prop.getProperty("url");
        RestAssured.basePath = "/";
    }

}
