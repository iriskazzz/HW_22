package tests.apiResponses;

import tests.models.LoginRequestModel;
import tests.models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static tests.specs.ReqResSpec.requestSpec;
import static tests.specs.ReqResSpec.responseSpec;

public class AuthorizationApi {
    public LoginResponseModel login(LoginRequestModel loginRequestModel){
        return given(requestSpec)
                .body(loginRequestModel)
                .contentType(JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
