package tests.models;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksListRequestModel {
  String userId;
  List<IsbnModel> collectionOfIsbns;
}
