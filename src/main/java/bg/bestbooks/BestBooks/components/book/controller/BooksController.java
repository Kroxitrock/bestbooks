package bg.bestbooks.BestBooks.components.book.controller;

import bg.bestbooks.BestBooks.components.book.service.BooksService;
import bg.bestbooks.BestBooks.components.book.service.dto.InputBookDto;
import bg.bestbooks.BestBooks.components.book.service.dto.InputCommentDto;
import bg.bestbooks.BestBooks.components.book.service.dto.OutputBookDto;
import bg.bestbooks.BestBooks.components.book.service.dto.OutputCommentDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BooksController {

  private final BooksService booksService;

  @Autowired
  public BooksController(BooksService booksService) {
    this.booksService = booksService;
  }

  @PostMapping(consumes = "application/json")
  public OutputBookDto createBook(@RequestBody InputBookDto book) {
    return booksService.create(book);
  }

  @GetMapping(produces = "application/json")
  public List<OutputBookDto> findBooks(
      @RequestParam(name = "query", defaultValue = "") String query) {
    return booksService.findBooks(query);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  public OutputBookDto findById(@PathVariable Integer id) {
    return booksService.findById(id);
  }

  @PutMapping(consumes = "application/json", path = "/{id}")
  public void updateBook(@PathVariable Integer id, @RequestBody InputBookDto book) {
    booksService.update(id, book);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteById(@PathVariable Integer id) {
    booksService.deleteById(id);
  }

  @GetMapping(path = "/{id}/comments")
  public List<OutputCommentDto> findComments(@PathVariable Integer id) {
    return booksService.findBooks(id);
  }

  @PostMapping(path = "/comments")
  public OutputCommentDto createComment(@RequestBody InputCommentDto comment) {
    return booksService.createComment(comment);
  }

  @PutMapping(path = "/comments/{id}")
  public OutputCommentDto updateComment(@PathVariable Long id,
      @RequestBody InputCommentDto comment) {
    return booksService.updateComment(id, comment);
  }

  @DeleteMapping(path = "/comments/{id}")
  public void deleteComment(@PathVariable Long id) {
    booksService.deleteComment(id);
  }


}
