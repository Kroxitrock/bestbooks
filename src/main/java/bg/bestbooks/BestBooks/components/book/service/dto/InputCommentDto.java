package bg.bestbooks.BestBooks.components.book.service.dto;

import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;

public class InputCommentDto {

  private String title;
  private String body;
  private Integer book;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setBook(Integer book) {
    this.book = book;
  }


  public Comment transformToEntity() {
    Comment comment = new Comment();
    comment.setTitle(title);
    comment.setBody(body);
    comment.setBook(new Book(book));
    return comment;
  }
}
