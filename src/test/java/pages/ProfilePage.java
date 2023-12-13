package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
  SelenideElement noDataTableText = $x("//*[contains(text(),'No rows found')]");

  public ProfilePage verifyNoDataTableText() {
    noDataTableText.shouldBe(visible);
    return this;
  }
}
