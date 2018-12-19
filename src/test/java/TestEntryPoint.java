import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;
import utilities.GetURL;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestEntryPoint {

    public Response response;

    @BeforeMethod
    public void setup() throws IOException {
        FactoryClass.Initialise();
        response = get(GetURL.POSTSENDPOINTURL);
        long timeInMs =response.time(); //Measure response time
    }

    @Test
    public void getUserDetailsAndValidateTheStatusCode() {
        response.
                then().
                statusCode(200).
                log().status();
    }

    @Test
    public void verifyIfResponseSchemaIsCorrect() {
            response
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("ModelSchema.json"))
                .log().body();
    }

    @Test
    public void verifyIfResponseResponseIsCorrect() {
        response
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("ModelSchema.json"))
                .log().body();
    }

    @AfterMethod
    public void tearDown(){
        RestAssured.reset();
    }


}
