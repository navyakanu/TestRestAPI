import io.restassured.RestAssured;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.when;

public class TestEntryPoint extends TestBase {

    Logger logger = Logger.getLogger(TestEntryPoint.class);

    @Test
    public void getUserDetails() {
        logger.debug("Executing test1-Get user credentials");
        when().
                get("/public-api/users?access-token=your_token").
                then().
                statusCode(200);
    }


}
