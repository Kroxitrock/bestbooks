package bg.bestbooks.BestBooks.components.book.service.dto;

import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import bg.bestbooks.BestBooks.components.user.model.User;
import java.util.ArrayList;
import java.util.List;

public class OutputCommentDto {

  private String title;
  private String body;
  private BookDto book;
  private UserDto user;

  public OutputCommentDto(Comment comment) {
    if (comment == null) {
      return;
    }
    title = comment.getTitle();
    body = comment.getBody();
    book = new BookDto(comment.getBook());
    user = new UserDto(comment.getUser());

  }

  public static List<OutputCommentDto> transferToDtoList(List<Comment> comments) {
    List<OutputCommentDto> outputCommentDtos = new ArrayList<>();
    comments.forEach((comment)
        -> outputCommentDtos.add(new OutputCommentDto(comment)));
    return outputCommentDtos;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }

  public BookDto getBook() {
    return book;
  }

  public UserDto getUser() {
    return user;
  }

  private static class BookDto {

    private Integer id;
    private String name;

    public BookDto(Book book) {
      id = book.getId();
      name = book.getName();
    }

    public Integer getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }

  private static class UserDto {

    private Long id;
    private String username;

    public UserDto(User user) {
      id = user.getId();
      username = user.getUsername();
    }

    public Long getId() {
      return id;
    }

    public String getUsername() {
      return username;
    }
  }

}
