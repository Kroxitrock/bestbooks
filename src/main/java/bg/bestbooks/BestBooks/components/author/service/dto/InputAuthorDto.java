package bg.bestbooks.BestBooks.components.author.service.dto;

import bg.bestbooks.BestBooks.components.author.model.Author;

public class InputAuthorDto {

  private String firstName;
  private String lastName;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Author transformToEntity() {
    Author author = new Author();
    author.setFirstName(firstName);
    author.setLastName(lastName);
    return author;
  }
}
