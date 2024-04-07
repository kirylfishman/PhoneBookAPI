package restassured;

import helpers.*;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetAllContactsRest implements TestHelper {
    @Test
    public static void GetAllContactsPositive(){
        AuthenticationResponseModel response = given()
                .contentType(ContentType.JSON)
                .when()
                .header(AuthorizationHeader, PropertiesReaderXML.getProperty("token"))
                .get(ADD_CONTACT_PATH)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(AuthenticationResponseModel.class);
        System.out.println("Token : " + response.getToken() );




    }

}
