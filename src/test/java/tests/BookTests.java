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

public class BookTests extends TestBase {

  AuthorizationApi authorizationApi = new AuthorizationApi();
  BooksApi booksApi = new BooksApi();
  LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);
  ProfilePage profilePage = new ProfilePage();

  @Test
  @WithLogin
  @DisplayName("Проверка удаления книги из коллекции через api")
  void deleteBookFromProfileTest() {
    step("Удаление всех книг из коллекции", () -> {
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
