package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import tests.apiResponses.AuthorizationApi;
import tests.apiResponses.BooksApi;
import tests.extensions.WithLogin;
import tests.models.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTests extends TestBase{

  AuthorizationApi authorizationApi = new AuthorizationApi();
  BooksApi booksApi = new BooksApi();
  LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);
  ProfilePage profilePage = new ProfilePage();

  @Test
  @WithLogin
  void deleteBookFromProfileTest() {
    step("Удаление всех книг в коллекции", () -> {
      booksApi.deleteAllBooks(loginResponse);
    });
    step("Добавление книги в коллекцию", () -> {
      List<IsbnModel> isbnList = new ArrayList<>();
      IsbnModel isbnModel = new IsbnModel(TestData.idBook);
      isbnList.add(isbnModel);

      AddBooksListRequestModel booksList = new AddBooksListRequestModel();
      booksList.setUserId(loginResponse.getUserId());
      booksList.setCollectionOfIsbns(isbnList);

      booksApi.addBook(loginResponse, booksList);
    });
    step("Удаление книги", () -> {
      DeleteBookResponseModel deleteBookModel = new DeleteBookResponseModel(TestData.idBook, TestData.idUser);
      booksApi.deleteBook(loginResponse, deleteBookModel);
    });
    step("Открытие страницы через UI и проверка отсутствия книги", () -> {
      open("/profile");
      profilePage.verifyNoDataTableText();
    });
  }
}
