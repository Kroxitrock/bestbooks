package bg.bestbooks.BestBooks.components.author.controller;

import bg.bestbooks.BestBooks.components.author.model.Author;
import bg.bestbooks.BestBooks.components.author.service.AuthorsService;
import java.util.List;
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
@RequestMapping("authors")
public class AuthorsController {
  private final AuthorsService authorsService;

  @Autowired
  public AuthorsController(AuthorsService authorsService) {
    this.authorsService = authorsService;
  }

  @GetMapping
  public List<Author> getAuthors() {
    return authorsService.findAll();
  }

  @GetMapping(path = "/{id}")
  public Author getAuthor(@PathVariable Integer id) {
    return authorsService.findById(id);
  }

  @PostMapping
  public Author createAuthor(@RequestBody Author author) {
    return authorsService.createAuthor(author);
  }

  @PutMapping(path = "/{id}")
  public Author updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
    return authorsService.updateAuthor(id, author);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteAuthor(@PathVariable Integer id) {
    authorsService.deleteAuthor(id);
  }
}
