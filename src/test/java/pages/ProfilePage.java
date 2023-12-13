package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    SelenideElement noDataTableText = $x("//*[contains(text(),'No rows found')]"),
            gitPocketBook = $("[id='see-book-Git Pocket Guide']"),
            userNameValue = $("#userName-value");

    public ProfilePage verifyNoDataTableText() {
        noDataTableText.shouldBe(visible);
        return this;
    }

    public ProfilePage verifyGitPocketBook() {
        gitPocketBook.shouldBe(visible);
        return this;
    }

    public ProfilePage verifyUserNameValue(String text) {
        userNameValue.shouldHave(text(text));
        return this;
    }
}
