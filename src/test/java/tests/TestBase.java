package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.models.LoginRequestModel;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
  LoginRequestModel loginRequestModel = new LoginRequestModel(TestData.LOGIN, TestData.PASSWORD);

  @BeforeAll
  static void beforeAll() {
    WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    RestAssured.baseURI = "https://demoqa.com/";
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.pageLoadStrategy = "eager";
    Configuration.remote = webConfig.getRemoteUrl();

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;

  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.addVideo();
    closeWebDriver();
  }

}
