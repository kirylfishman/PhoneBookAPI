import helpers.*;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class RegistrationTestPositive  {

   @Test
public void RegistrationPositive() throws IOException {


    AuthenticationRequestModel requestModel = AuthenticationRequestModel
            .username(EmailGenerator.generateEmail(4,4,4))

            .password(PasswordGenerator.generateString());

    RequestBody requestBody = RequestBody
            .create(TestConfig.gson.toJson(requestModel),
                    TestConfig.JSON);
    Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
            .post(requestBody)
            .build();
    Response response = TestConfig.client.newCall(request).execute();
    System.out.println("Response code: " + response.code());
    if (response.isSuccessful()) {

        AuthenticationResponseModel responseModel =
                TestConfig.gson.fromJson(response.body().string(),
                        AuthenticationResponseModel.class);
        PropertiesWriterRegistration.writeProperties("tokenRegistration", responseModel.getToken(), false);
        Assert.assertTrue(response.isSuccessful());
    } else {
        System.out.println("Error");
    }
}
}
