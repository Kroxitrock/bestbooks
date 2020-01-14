package bg.bestbooks.BestBooks.components.book.service.dto;

import bg.bestbooks.BestBooks.components.author.model.Author;
import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import java.util.ArrayList;
import java.util.List;

public class OutputBookDto {

  private String name;
  private String description;
  private String cover;
  private Short pages;
  private AuthorDto author;
  private List<Comment> comments;

  public OutputBookDto(Book book) {
    if (book == null) {
      return;
    }
    name = book.getName();
    description = book.getDescription();
    cover = book.getCover();
    pages = book.getPages();
    author = new AuthorDto(book.getAuthor());
    comments = book.getComments();
  }

  public static List<OutputBookDto> transferToDtoList(List<Book> books) {
    List<OutputBookDto> outputBookDtos = new ArrayList<>();
    books.forEach((book)
        -> outputBookDtos.add(new OutputBookDto(book)));
    return outputBookDtos;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getCover() {
    return cover;
  }

  public Short getPages() {
    return pages;
  }

  public AuthorDto getAuthor() {
    return author;
  }

  public List<Comment> getComments() {
    return comments;
  }

  private static class AuthorDto {

    private Integer id;
    private String firstName;
    private String lastName;

    public AuthorDto(Author author) {
      id = author.getId();
      firstName = author.getFirstName();
      lastName = author.getLastName();
    }

    public Integer getId() {
      return id;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

  }
}
