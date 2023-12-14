package extensions;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import tests.TestData;
import apiResponses.AuthorizationApi;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) {
    LoginRequestModel loginRequestModel = new LoginRequestModel(TestData.LOGIN, TestData.PASSWORD);
    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);

    open("/favicon.ico");
    getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
    getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
    getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
  }
}
