package tests.models;

import lombok.Data;

@Data
public class DeleteBookResponseModel {

    public DeleteBookResponseModel(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }
    String isbn, userId;

}
