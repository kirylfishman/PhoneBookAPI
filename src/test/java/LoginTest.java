import helpers.PropertiesReader;
import helpers.PropertiesWriter;
import helpers.TestConfig;
import helpers.TestHelper;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest implements TestHelper {

    @Test
    public void loginPositive() throws IOException {

        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReader.getProperty("existinguseremail"))
                .password(PropertiesReader.getProperty("existingUserPassword"));
        RequestBody requestBody = RequestBody
                .create(TestConfig.gson.toJson(requestModel),
                        TestConfig.JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();


        Response response = TestConfig.client.newCall(request).execute();
        System.out.println("Response code: " + response.code());
        if(response.isSuccessful()){

            AuthenticationResponseModel responseModel =
                    TestConfig.gson.fromJson(response.body().string(),
                            AuthenticationResponseModel.class);
            PropertiesWriter.writeProperties("token",responseModel.getToken(),false);


            Assert.assertTrue(response.isSuccessful());
        }
        else {
            System.out.println("Status code : " + response.code());
            ErrorModel errorModel = gson.fromJson(response.body().string(), ErrorModel.class);
            System.out.println("Error status: " +errorModel.getStatus());
        }


    }
}