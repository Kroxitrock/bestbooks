package bg.bestbooks.components.book.model;

import bg.bestbooks.components.author.model.Author;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String description;

  @Lob
  @Column(name = "cover", columnDefinition="BLOB")
  private byte[] cover;

  private Short pages;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

  @OneToMany(mappedBy = "book")
  private List<Comment> comments;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public byte[] getCover() {
    return cover;
  }

  public void setCover(byte[] cover) {
    this.cover = cover;
  }

  public Short getPages() {
    return pages;
  }

  public void setPages(Short pages) {
    this.pages = pages;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
