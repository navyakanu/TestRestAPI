import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import utilities.GetURL;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class TestPOSTAPI {

    public Response response;


    @BeforeMethod
    public void BeforeMethod() throws IOException {
        FactoryClass.Initialise();
        response = post(GetURL.POSTSENDPOINTURL);
        long timeInMs = response.time();

    }


    public void verifyStatusCodeForPost() {
        given().
                formParam("formParamName", "value1").
                queryParam("queryParamName", "value2").
        when().
                post("/something").
        then().
                statusCode(200);
    }


    public void verifySchemaForPostResponse() {
        given().
                formParam("formParamName", "value1").
                queryParam("queryParamName", "value2").
        when().
                post("/something").
        then().
                statusCode(200);
    }


}
