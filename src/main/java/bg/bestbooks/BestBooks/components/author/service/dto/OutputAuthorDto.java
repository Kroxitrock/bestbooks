package bg.bestbooks.BestBooks.components.author.service.dto;

import bg.bestbooks.BestBooks.components.author.model.Author;
import java.util.ArrayList;
import java.util.List;

public class OutputAuthorDto {

  private String firstName;
  private String lastName;

  public OutputAuthorDto(Author author) {
    if (author == null) {
      return;
    }
    firstName = author.getFirstName();
    lastName = author.getLastName();
  }

  public static List<OutputAuthorDto> transferToDtoList(List<Author> authors) {
    List<OutputAuthorDto> outputAuthorDtos = new ArrayList<>();
    authors.forEach((author)
        -> outputAuthorDtos.add(new OutputAuthorDto(author)));
    return outputAuthorDtos;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
