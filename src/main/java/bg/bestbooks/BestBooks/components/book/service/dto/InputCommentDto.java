package bg.bestbooks.BestBooks.components.book.service.dto;

import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import bg.bestbooks.BestBooks.components.user.model.User;

public class InputCommentDto {

  private String title;
  private String body;
  private BookDto book;
  private UserDto user;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setBook(BookDto book) {
    this.book = book;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public Comment transformToEntity() {
    Comment comment = new Comment();
    comment.setTitle(title);
    comment.setBody(body);
    comment.setBook(book.transformToEntity());
    comment.setUser(user.transformToEntity());
    return comment;
  }

  private static class BookDto {

    private Integer id;

    public Book transformToEntity() {
      Book book = new Book();
      book.setId(id);
      return book;
    }
  }

  private static class UserDto {

    private Long id;

    public User transformToEntity() {
      User user = new User();
      user.setId(id);
      return user;
    }
  }

}
