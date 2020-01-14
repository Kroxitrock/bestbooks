package bg.bestbooks.BestBooks.components.book.service;

import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import bg.bestbooks.BestBooks.components.book.repository.BooksRepository;
import bg.bestbooks.BestBooks.components.book.repository.CommentsRepository;
import bg.bestbooks.BestBooks.components.book.service.dto.InputBookDto;
import bg.bestbooks.BestBooks.components.book.service.dto.InputCommentDto;
import bg.bestbooks.BestBooks.components.book.service.dto.OutputBookDto;
import bg.bestbooks.BestBooks.components.book.service.dto.OutputCommentDto;
import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.repository.UsersRepository;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

  private final BooksRepository booksRepository;

  private final CommentsRepository commentsRepository;

  private final UsersRepository usersRepository;

  @Autowired
  public BooksService(BooksRepository booksRepository, CommentsRepository commentsRepository, UsersRepository usersRepository) {
    this.booksRepository = booksRepository;
    this.commentsRepository = commentsRepository;
    this.usersRepository = usersRepository;
  }

  public List<OutputBookDto> findBooks(String query) {
    return OutputBookDto.transferToDtoList(booksRepository.findByNameContaining(query));
  }

  public OutputBookDto findById(Integer id) {
    Book book = booksRepository.findById(id).orElseThrow(NoSuchElementException::new);
    return new OutputBookDto(book);
  }

  @Transactional
  public OutputBookDto create(InputBookDto inputBook) {
    Book book = inputBook.transformToEntity();
    book = booksRepository.save(book);
    return new OutputBookDto(book);
  }

  @Transactional
  public OutputBookDto update(Integer id, InputBookDto inputBookDto) {
    Book book = booksRepository.findById(id).orElseThrow(NoSuchElementException::new);
    book = inputBookDto.transformToEntity();
    book.setId(id);
    book = booksRepository.save(book);
    return new OutputBookDto(book);
  }

  @Transactional
  public void deleteById(Integer id) {
    booksRepository.deleteById(id);
  }

  public List<OutputCommentDto> findBooks(Integer bookId) {
    return OutputCommentDto.transferToDtoList(commentsRepository.findAllByBook_Id(bookId));
  }

  @Transactional
  public OutputCommentDto createComment(InputCommentDto inputComment, Principal principal)
      throws AuthenticationException {
    User user = usersRepository.findByUsername(principal.getName()).orElseThrow(AuthenticationException::new);
    Comment comment = inputComment.transformToEntity();
    comment.setUser(user);
    comment = commentsRepository.save(comment);
    return new OutputCommentDto(comment);
  }

  @Transactional
  public OutputCommentDto updateComment(Long id, InputCommentDto inputComment) {
    Comment comment = commentsRepository.findById(id).orElseThrow(NoSuchElementException::new);
    comment = inputComment.transformToEntity();
    comment.setId(id);
    comment = commentsRepository.save(comment);
    return new OutputCommentDto(comment);
  }

  @Transactional
  public void deleteComment(Long id) {
    commentsRepository.deleteById(id);
  }
}
