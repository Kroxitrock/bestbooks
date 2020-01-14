package bg.bestbooks.BestBooks.components.book.service.dto;

import bg.bestbooks.BestBooks.components.author.model.Author;
import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import java.util.List;

public class InputBookDto {

  private String name;
  private String description;
  private String cover;
  private Short pages;
  private AuthorDto author;
  private List<Comment> comments;


  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public void setAuthor(AuthorDto author) {
    this.author = author;
  }

  public void setPages(Short pages) {
    this.pages = pages;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }


  public Book transformToEntity() {
    Book book = new Book();
    book.setName(name);
    book.setDescription(description);
    book.setCover(cover);
    book.setPages(pages);
    book.setAuthor(author.transformToEntity());
    book.setComments(comments);
    return book;
  }

  private static class AuthorDto {

    private Integer id;

    public void setId(Integer id) {
      this.id = id;
    }

    public Author transformToEntity() {
      Author author = new Author();
      author.setId(id);
      return author;
    }
  }

}
