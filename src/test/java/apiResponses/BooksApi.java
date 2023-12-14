package apiResponses;

import models.AddBooksListRequestModel;
import models.DeleteBookResponseModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.ReqResSpec.requestSpec;
import static specs.ReqResSpec.responseSpec;

public class BooksApi {
  public void deleteAllBooks(LoginResponseModel loginResponse) {
    given(requestSpec)
            .contentType(JSON)
            .header("Authorization", "Bearer " + loginResponse.getToken())
            .queryParam("UserId", loginResponse.getUserId())
            .when()
            .delete("/BookStore/v1/Books")
            .then()
            .spec(responseSpec)
            .statusCode(204);
  }

  public void addBook(LoginResponseModel loginResponse, AddBooksListRequestModel booksList) {
    given()
            .contentType(JSON)
            .header("Authorization", "Bearer " + loginResponse.getToken())
            .body(booksList)
            .when()
            .post("/BookStore/v1/Books")
            .then()
            .spec(responseSpec)
            .statusCode(201);
  }

  public void deleteBook(LoginResponseModel loginResponseModel, DeleteBookResponseModel deleteModel) {
    given()
            .contentType(JSON)
            .header("Authorization", "Bearer " + loginResponseModel.getToken())
            .body(deleteModel)
            .when()
            .delete("/BookStore/v1/Book")
            .then()
            .statusCode(204);
  }
}
