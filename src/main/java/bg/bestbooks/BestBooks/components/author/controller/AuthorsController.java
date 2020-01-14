package bg.bestbooks.BestBooks.components.author.controller;

import bg.bestbooks.BestBooks.components.author.service.AuthorsService;
import bg.bestbooks.BestBooks.components.author.service.dto.InputAuthorDto;
import bg.bestbooks.BestBooks.components.author.service.dto.OutputAuthorDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authors")
public class AuthorsController {

  private final AuthorsService authorsService;

  @Autowired
  public AuthorsController(AuthorsService authorsService) {
    this.authorsService = authorsService;
  }

  @GetMapping
  public List<OutputAuthorDto> getAuthors() {
    return authorsService.findAll();
  }

  @GetMapping(path = "/{id}")
  public OutputAuthorDto getAuthor(@PathVariable("id") Integer id) {
    return authorsService.findById(id);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public OutputAuthorDto createAuthor(@RequestBody InputAuthorDto author) {
    return authorsService.createAuthor(author);
  }

  @PutMapping(path = "/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public OutputAuthorDto updateAuthor(@PathVariable("id") Integer id,
      @RequestBody InputAuthorDto author) {
    return authorsService.updateAuthor(id, author);
  }

  @DeleteMapping(path = "/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public void deleteAuthor(@PathVariable("id") Integer id) {
    authorsService.deleteAuthor(id);
  }
}
