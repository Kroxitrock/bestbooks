package bg.bestbooks.BestBooks.components.book.controller;

import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import bg.bestbooks.BestBooks.components.book.service.BooksService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {

  private final BooksService booksService;

  @Autowired
  public BooksController(BooksService booksService) {
    this.booksService = booksService;
  }

  @PostMapping(consumes = "application/json")
  public Book createBook(@RequestBody Book book) {
    System.out.println(book);
    return booksService.create(book);
  }

  @GetMapping(produces = "application/json")
  public List<Book> findAll() {
    return booksService.findAll();
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  public Book findById(@PathVariable Integer id) {
    Optional<Book> book = booksService.findById(id);

    return book.orElse(null);
  }

  @PutMapping(consumes = "application/json", path = "/{id}")
  public void updateBook(@PathVariable Integer id, @RequestBody Book book) {
    booksService.update(id, book);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteById(@PathVariable Integer id) {
    booksService.deleteById(id);
  }

  @GetMapping(path = "/{id}/comments")
  public List<Comment> findComments(@PathVariable Integer id) {
    return booksService.findAll(id);
  }

  @PostMapping(path = "/comments")
  public Comment createComment(@RequestBody Comment comment) {
    return booksService.createComment(comment);
  }

  @PutMapping(path = "/comments/{id}")
  public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
    return booksService.updateComment(id, comment);
  }

  @DeleteMapping(path = "/comments/{id}")
  public void deleteComment(@PathVariable Long id) {
    booksService.deleteComment(id);
  }


}
