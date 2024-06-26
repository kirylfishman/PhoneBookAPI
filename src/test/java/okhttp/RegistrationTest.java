package okhttp;

import helpers.*;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest implements TestHelper {

    @Test
    public void RegistrationPositive() throws IOException {
        NewUserModel newUserModel = new NewUserModel(EmailGenerator.generateEmail(1,4,4),
                PasswordGenerator.generateString());
        RequestBody requestBody = RequestBody.create(gson.toJson(newUserModel),JSON);
        Request request = new Request.Builder()
                .url(RegistrationPath)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        System.out.println("Response : " + res);
        if (response.isSuccessful()){
            AuthenticationResponseModel responseModel = gson.fromJson(res,AuthenticationResponseModel.class);
            String resultToken = responseModel.getToken();
            //System.out.println("Token: " + responseModel.getToken());
            System.out.println("Code: " + response.code());
            PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML(PROPERTIES_PATH);
            propertiesWriterXML.setProperties(TOKEN_KEY,resultToken,false );
            Assert.assertTrue(response.isSuccessful());
        }
        else {
            ErrorModel errorModel = gson.fromJson(res,ErrorModel.class);
            System.out.println(errorModel.getStatus());
            System.out.println(errorModel.getError());
            System.out.println(errorModel.getMessage());
            System.out.println(response.code());
        }
    }

}
