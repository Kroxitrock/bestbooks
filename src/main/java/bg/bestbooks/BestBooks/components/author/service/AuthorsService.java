package bg.bestbooks.BestBooks.components.author.service;

import bg.bestbooks.BestBooks.components.author.model.Author;
import bg.bestbooks.BestBooks.components.author.repository.AuthorsRepository;
import bg.bestbooks.BestBooks.components.author.service.dto.InputAuthorDto;
import bg.bestbooks.BestBooks.components.author.service.dto.OutputAuthorDto;
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

  public List<OutputAuthorDto> findAll() {
    return OutputAuthorDto.transferToDtoList(authorsRepository.findAll());
  }

  public OutputAuthorDto findById(Integer id) {
    Author author = authorsRepository.findById(id).orElse(null);
    return new OutputAuthorDto(author);
  }

  @Transactional
  public OutputAuthorDto createAuthor(InputAuthorDto inputAuthor) {
    Author author = inputAuthor.transformToEntity();
    author = authorsRepository.save(author);
    return new OutputAuthorDto(author);
  }

  @Transactional
  public OutputAuthorDto updateAuthor(Integer id, InputAuthorDto inputAuthor) {
    if (authorsRepository.existsById(id)) {
      Author author = inputAuthor.transformToEntity();
      author.setId(id);
      author = authorsRepository.save(author);
      return new OutputAuthorDto(author);
    }

    throw new NoSuchElementException();
  }

  @Transactional
  public void deleteAuthor(Integer id) {
    authorsRepository.deleteById(id);
  }

}
