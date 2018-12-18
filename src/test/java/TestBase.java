import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.PropertiesLoader;

import java.io.IOException;


public class TestBase {



    @BeforeClass
    public void setup() throws IOException {

        PropertiesLoader.LoadProperties();
        RestAssured.baseURI = PropertiesLoader.prop.getProperty("url");
        RestAssured.baseURI = "https://gorest.co.in";
        System.out.println("Entry to the api tests");
        //RestAssured.port = Integer.parseInt(System.getProperty("api.host.port",""));
        RestAssured.basePath = "/";
    }


    @AfterClass
    public void teardown() {
        RestAssured.reset();

    }

}
