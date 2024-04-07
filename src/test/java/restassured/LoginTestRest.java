package restassured;

import helpers.EmailGenerator;
import helpers.PasswordGenerator;
import helpers.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.NewUserModel;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class LoginTestRest {
    @Test
    public void registrationTestRestAssured(){

        NewUserModel newUserModel = new NewUserModel (EmailGenerator.generateEmail(4,4,4),
                PasswordGenerator.generateString());
       String token =  given().body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().path("token");
        System.out.println("Token: " + token);


    }

    @Test
    public void loginTestRestAssured(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        AuthenticationRequestModel authenticationRequestModel = AuthenticationRequestModel
                .username(PropertiesReader.getProperty("existinguseremail"))
                .password(PropertiesReader.getProperty("existingUserPassword"));
        AuthenticationResponseModel response = given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post()
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
