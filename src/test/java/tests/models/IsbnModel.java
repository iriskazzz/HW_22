package tests.models;

import lombok.Data;

@Data
public class IsbnModel {

    public IsbnModel(String isbn) {
        this.isbn = isbn;
    }
    String isbn;
}
