import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import utilities.GetModelSchema;
import utilities.GetURL;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestGETAPI {

    public Response response;
    public RequestSpecification httpRequest;

    @BeforeMethod
    public void setup() throws IOException {
        FactoryClass.Initialise();
        response = getAPIResponse();
        long timeInMs =response.time(); //Measure response time
    }

    public static Response getAPIResponse(){
        return given().
                headers("Content-Type","application/json\"").
        when().
                get(GetURL.POSTSENDPOINTURL).
        then().
                extract().response();
    }


    @Test
    public void getUserDetailsAndValidateTheStatusCode() {
        response.
                then().
                    contentType(ContentType.JSON).
                    statusCode(200).
                log().status();
    }

    @Test
    public void verifyIfResponseSchemaIsCorrect() {
        response
                .then()
                    .assertThat().body(matchesJsonSchemaInClasspath(GetModelSchema.modelschema))
                    .log().body();
    }

    @Test
    public void verifyIfResponseLengthIsCorrect() {
        //Use query param for get only 1 element response data
        List<String> jsonResponse = response.jsonPath().getList("$");
        System.out.println(jsonResponse.size());
        //Need to check what to assert (Depends on the test)
    }

    @Test
    public void verifyIfResponseContentIsCorrect() {
        //Use query param for get only 1 element response data
        String jsonResponse = response.jsonPath().getString("title[0]");
        assertEquals(jsonResponse,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit");

        //Other references -List
        //List<String> jsonResponse = response.jsonPath().getList("username");
        //System.out.println(jsonResponse.get(0));

        //Map
       // Map<String, String> company = response.jsonPath().getMap("company");
       // System.out.println(company.get("name"));
        //Array
        //Map<String, String> company = response.jsonPath().getMap("company[0]");
        //System.out.println(company.get("name"));


    }

    @AfterMethod
    public void tearDown(){
        RestAssured.reset();
    }


}
