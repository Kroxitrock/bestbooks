package bg.bestbooks.BestBooks.components.author.service;

import bg.bestbooks.BestBooks.components.author.model.Author;
import bg.bestbooks.BestBooks.components.author.repository.AuthorsRepository;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorsService {
  private final AuthorsRepository authorsRepository;

  @Autowired
  public AuthorsService(AuthorsRepository authorsRepository) {
    this.authorsRepository = authorsRepository;
  }

  public List<Author> findAll() {
    return authorsRepository.findAll();
  }

  public Author findById(Integer id) {
    return authorsRepository.findById(id).orElse(null);
  }

  @Transactional
  public Author createAuthor(Author author) {
    return authorsRepository.save(author);
  }

  @Transactional
  public Author updateAuthor(Integer id, Author author) {
    if (authorsRepository.existsById(id)) {
      author.setId(id);
      authorsRepository.save(author);
    }

    throw new NoSuchElementException();
  }

  @Transactional
  public void deleteAuthor(Integer id) {
    authorsRepository.deleteById(id);
  }

}
